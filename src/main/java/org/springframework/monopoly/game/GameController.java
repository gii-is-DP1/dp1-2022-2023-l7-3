package org.springframework.monopoly.game;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Auction;
import org.springframework.monopoly.property.AuctionForm;
import org.springframework.monopoly.property.Color;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
	
	private static final String VIEWS_NEW_GAME = "game/newGame";
	private static final String GAME_MAIN = "game/gameMain";
	private static final String BLANK_GAME = "game/blankGame"; //este es provisional para los tags
	public static final String GAMES_LISTING="game/GameList";
	
	private GameService gameService;
	private PlayerService playerService;
	private UserService userService;
	private TurnService turnService;
	private StreetService streetService;
	private PropertyService propertyService;
	
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, UserService userService, TurnService turnService,
			StreetService streetService, PropertyService propertyService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
		this.turnService = turnService;
		this.streetService = streetService;
		this.propertyService = propertyService;
	}

	//PROVISIONAL
	@GetMapping(value = "/blankGame")
	public String blankGame(Map<String, Object> model, Authentication authentication) {
		Integer idProperty = 12;
		Integer idGame = 2;
		Integer idPlayer = 5;
		Object property = propertyService.getProperty(idProperty, idGame);
		List<Integer> players = gameService.findGame(idGame).get().getPlayers().stream().map(p-> p.getId()).collect(Collectors.toList());
		Player player = playerService.findPlayerById(players.get(0));
		Auction auction = new Auction(0, players, 10, 0, idProperty); 
		List<Color> colors= propertyService.findPlayerColors(idGame, idPlayer);
		List<Property> properties= new ArrayList<>();
		for (Color c: colors) {
			propertyService.findStreetByColor(c, idGame).forEach(x -> properties.add(x));;
		}

		model.put("properties", properties );
		model.put("property", property );
		model.put("auction", auction);
		model.put("player", player);
		
		return BLANK_GAME;
	}
	
	@PostMapping(value = "/blankGame")
	public String auction( AuctionForm auction, Map<String, Object> model, Authentication authentication) {
		Integer idGame = 2;
		Object property = propertyService.getProperty(auction.getPropertyId(), idGame);
		Auction oldAuction = new Auction(auction.getPlayerIndex(), auction.getRemainingPlayers(), auction.getCurrentBid(), auction.getPlayerBid(), auction.getPropertyId());
		Auction newAuction = propertyService.auctionPropertyById(oldAuction);
		if(newAuction == null) {
			return GAMES_LISTING;
		}
		model.put("property", property );
		model.put("auction", newAuction);
		model.put("player", playerService.findPlayerById(newAuction.getPlayerIndex()));
		
		return BLANK_GAME;
	}
	
	@GetMapping(value = "/newGame")
	public String newGame(Map<String, Object> model, Authentication authentication) {
		List<User> users = userService.findAll();
		
		User creatorUser = userService.findUserByName(authentication.getName());
		users.remove(creatorUser);
		
		List<User> players = new ArrayList<User>();
		players.add(creatorUser);
		
		model.put("users", users);
		model.put("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/add/{userId}")
	public String postCreatingGameAddPlayer(@PathVariable("userId") int userId, GameForm gameForm, Map<String, Object> model) {
		User addedUser = userService.findUser(userId).get();
		
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.add(addedUser);
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.put("users", users);
		model.put("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/remove/{playerNum}")
	public String creatingGameRemovePlayer(@PathVariable("playerNum") int playerNum, GameForm gameForm, Map<String, Object> model) {
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.remove(playerNum);
		
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.put("users", users);
		model.put("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame")
	public String processGameForm(GameForm gameForm, Map<String, Object> model, BindingResult result) {
		Game savedGame;
		try {
			savedGame = gameService.setUpNewGame(gameForm);
//			gameService.saveGame(savedGame);
			savedGame = gameService.setUpNewGamePlayers(gameForm.getUsers(), savedGame);
//			savedGame = gameService.saveGame(savedGame);
			savedGame = gameService.setProperties(savedGame);
			savedGame = gameService.saveGame(savedGame);
			
			Street s = savedGame.getStreets().stream().findFirst().orElse(null);
			streetService.findStreet(s.equals(null) ? null : s.getId(), savedGame.getId());
			
		} catch (InvalidNumberOfPLayersException e) {
			result.rejectValue("Users[0]", "Not enough players", "There are not enough players to start");
			return VIEWS_NEW_GAME;
		}
		
		return "redirect:/game/" + savedGame.getId();
	}
	
	@GetMapping(value = "/game/{gameId}")
	public String loadGame(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		Optional<Game> gameOptional = gameService.findGame(gameId);
		if(gameOptional.isEmpty()) {
			throw new Exception("Game doesn't exist");
		}  
		Game game = gameOptional.get();
		 
		User requestUser = userService.findUserByName(auth.getName());
		
		List<Player> players = new ArrayList<Player>(game.getPlayers());
		Comparator<Player> c = Comparator.comparing(p -> p.getTurnOrder());
		Collections.sort(players, c);

		//esto se va al turn service como nexturn no se que
		Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
		Turn turn = new Turn();
		turn.setGame(game);
		Player nextPlayer;
		Boolean isPlaying = false;
		
		// Calculating next turn result
		if(lastTurn != null) {
			nextPlayer = players.get((players.indexOf(lastTurn.getPlayer()) + 1) % players.size());
			if(lastTurn.getIsFinished() && nextPlayer.getUser().equals(requestUser)) {
				turn.setPlayer(nextPlayer);
				turn.setInitial_tile(nextPlayer.getTile());
				turn.setTurnNumber(lastTurn.getTurnNumber() + 1);
				turnService.calculateTurn(turn);
				isPlaying = nextPlayer.getUser().equals(requestUser);
			} else if(lastTurn.getIsFinished() && !nextPlayer.getUser().equals(requestUser)) {
				isPlaying = nextPlayer.getUser().equals(requestUser);
				turn = lastTurn;
			} else {
				isPlaying = lastTurn.getPlayer().getUser().equals(requestUser);
				turn = lastTurn;
			} 
			
		} else {
			// Esto se podría pasar al metodo de crear partida
			nextPlayer = players.get(0);
			isPlaying = nextPlayer.getUser().equals(requestUser);
			turn.setPlayer(nextPlayer);
			turn.setInitial_tile(nextPlayer.getTile());
			turn.setTurnNumber(0);
			turnService.calculateTurn(turn);
			
		}
		
		model.addAttribute("Game", game);
		model.addAttribute("Turn", turn);
		model.addAttribute("Players", players);
		
		// Temporal for debugging purposes
		isPlaying = true; 
		
		// To show the end turn button and popups if there is any
		model.addAttribute("isPlaying", isPlaying);
		

		//esto es una query de todos los nombre
		List<List<String>> properties = new ArrayList<List<String>>();
		for(Player p:players) {
			properties.add(playerService.findPlayerPropertiesNames(p));
		}

		// public List<Property> getProperties() {
		// 	List<Property> res = new ArrayList<Property>();
		// 	for(Street s:this.getStreets()) {
		// 		res.add((Property) s);
		// 	}
		// 	for(Company c:this.getCompanies()) {
		// 		res.add((Property) c);
		// 	}
		// 	for(Station st:this.getStations()) {
		// 		res.add((Property) st);
		// 	}
		// 	return res;
		// }
		    
		model.addAttribute("Properties", properties);
		    
		// Street colors
		// hacer esto de otra forma aparte
 		List<List<Color>> colors = new ArrayList<List<Color>>();
		for(Player p:players) {
			colors.add(streetService.findPlayerColors(p));
		}
 		model.addAttribute("Colors", colors);
   		
		return GAME_MAIN;
	} 
	
	@GetMapping(value = "/game/{gameId}/evalTurn")
	public String evalTurn(@PathVariable("gameId") int gameId, /* Object turnForm Make individual methods,*/ Authentication auth, Model model) throws Exception {
		
		// Get user that made the request
		Integer requestUserId = userService.findUserByName(auth.getName()).getId();
		
		// Get the user of the turn that is being played
		User turnUser = null;
		try {
			Turn lastTurn = turnService.findLastTurn(gameId).get();
			turnUser = lastTurn.getPlayer().getUser();
		} catch (Exception e) {
			return "redirect:/game/" + gameId;
		}
		
		// If the user is the turn user continue
		if(requestUserId == turnUser.getId()) {
			// Calcultate turn results
			
			// Load model with everything necessary
			
			return loadGame(gameId, auth, model);
		} else {
			return "redirect:/game/" + gameId;
		}
	}
	
	// TODO start thinking on this
	@GetMapping(value = "/game/{gameId}/endTurn")
	public String endTurn(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		Turn turn = turnService.findLastTurn(gameId).get();
		User requestUser = userService.findUserByName(auth.getName());
		
		if(turn.getPlayer().getUser().equals(requestUser) && !turn.getIsFinished()) {
			turn.setIsFinished(true);
			
			turn.getPlayer().setTile(turn.getInitial_tile()+turn.getRoll());
			playerService.savePlayer(turn.getPlayer());
			
			turnService.saveTurn(turn);
		}
		
		return "redirect:/game/" + gameId;
//		// Find all details needed to show to users
//		Integer requestUserId = userService.findUserByName(auth.getName()).getId();
//		
//		// Get the user of the turn that 
//		
//		
//		if(currentUser.getId().equals(requestUserId)) {
//			
//			Optional<Game> gameOptional = gameService.findGame(gameId);
//			if(gameOptional.isEmpty()) {
//				throw new Exception("Game doesn't exist");
//			} 
//			Game game = gameOptional.get();
//			
//			List<Player> players = new ArrayList<Player>(game.getPlayers());
//			Comparator<Player> c = Comparator.comparing(p -> p.getTurnOrder());
//			Collections.sort(players, c);
//			Optional<Turn> lastTurnOpt = turnService.findLastTurn(gameId);
//			
//			Turn nextTurn = new Turn();
//			Player nextPlayer = null;
//			
//			// Calculating next turn result
//			if(lastTurnOpt.isPresent()) {
//				Turn lastTurn = lastTurnOpt.get();
//				nextTurn.setTurnNumber(lastTurn.getTurnNumber() + 1);
//				nextPlayer = players.get((players.indexOf(lastTurn.getPlayer()) + 1) % players.size());
//			} else {
//				nextTurn.setTurnNumber(0);
//				nextPlayer = players.get(0);
//			} 
//			
//			nextTurn.setGame(game);
//			nextTurn.setPlayer(nextPlayer);
//			nextTurn.setInitial_tile(nextPlayer.getTile());
//			nextTurn = turnService.calculateTurn(nextTurn);
//			
//			turnService.saveTurn(nextTurn);
//			
//			model.addAttribute("Game", game);
//			model.addAttribute("Turn", nextTurn);
//			model.addAttribute("Players", game.getPlayers());
//	
//			return GAME_MAIN;
//		} else {
//			return "redirect:/game/" + gameId;
//		}
	}
		
    @GetMapping("/games/list")
    public ModelAndView showGamesListing(@RequestParam Map<String, Object> params, Principal auth) {
    	int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())) : 0;
		Page<Game> pageGame = gameService.getAll(PageRequest.of(page, 5), auth.getName());

        ModelAndView result=new ModelAndView(GAMES_LISTING);
        result.addObject("games", pageGame.getContent());
        
        int totalPages = pageGame.getTotalPages();
        
        if(totalPages > 0) {
			List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());
			result.addObject("pages", pages);
		}
        
        return result;
    }
	   	
}
