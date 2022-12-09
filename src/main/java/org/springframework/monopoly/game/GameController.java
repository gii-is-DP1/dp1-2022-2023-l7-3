package org.springframework.monopoly.game;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Auction;
import org.springframework.monopoly.property.Color;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.tile.ExitGateForm;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.tile.TileService;
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
	private TileService tileService;
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, UserService userService, TurnService turnService,
			StreetService streetService, PropertyService propertyService, TaxesService taxesService, TileService tileService,
			CardService cardService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
		this.turnService = turnService;
		this.streetService = streetService;
		this.propertyService = propertyService;
		this.tileService = tileService;
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
		Auction auction = new Auction(0, players, 10, 0, idProperty, idGame); 
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
	public String auction( Auction auction, Map<String, Object> model, Authentication authentication) {
		Integer idGame = 2;
		Object property = propertyService.getProperty(auction.getPropertyId(), idGame);
		Auction newAuction = propertyService.auctionPropertyById(auction);
		if(newAuction == null || newAuction.getRemainingPlayers().size() == 1) {
			//propertyService.setAuctionWinner(newAuction, turnService.findLastTurn(idGame).get());
			return GAMES_LISTING;
		}
		model.put("property", property );
		model.put("auction", newAuction);
		model.put("player", playerService.findPlayerById(newAuction.getRemainingPlayers().get(newAuction.getPlayerIndex())));
		
		return BLANK_GAME;
	}
	
	// Auction method with the real game mapping
	@PostMapping(value = "/game/{gameId}/auction")
	public String auctionFinal(@PathVariable("gameId") int gameId, Auction auction, Model model, Authentication authentication) throws Exception {
		Object property = propertyService.getProperty(auction.getPropertyId(), gameId);
		Auction newAuction = propertyService.auctionPropertyById(auction);
		if(newAuction == null || newAuction.getRemainingPlayers().size() == 1) {
			//propertyService.setAuctionWinner(newAuction, turnService.findLastTurn(idGame).get());
			return GAMES_LISTING;
		}
		model.addAttribute("property", property);
		model.addAttribute("auction", newAuction);
		model.addAttribute("player", playerService.findPlayerById(newAuction.getRemainingPlayers().get(newAuction.getPlayerIndex())));
		
		model = gameService.setupGameModel(model, gameId, authentication);
		
		return GAME_MAIN;
	}
	
	@PostMapping(value = "/exitGate")
	public String getOutOfJail(ExitGateForm exitGateForm, Map<String, Object> model, Authentication authentication) {
		
		Integer option = exitGateForm.getOption();
		tileService.calculateActionTile(null, option);
		
		return GAMES_LISTING;
	}

	
	@GetMapping(value = "/newGame")
	public String newGame(Model model, Authentication authentication) throws Exception {
		List<User> users = userService.findAll();
		
		User creatorUser = userService.findUserByName(authentication.getName()).orElse(null);
		if(creatorUser == null) {
			throw new Exception("No such user found for that principal");
		}
		users.remove(creatorUser);
		
		List<User> players = new ArrayList<User>();
		players.add(creatorUser);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/add/{userId}")
	public String postCreatingGameAddPlayer(@PathVariable("userId") int userId, GameForm gameForm, Model model) {
		User addedUser = userService.findUser(userId).get();
		
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.add(addedUser);
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/remove/{playerNum}")
	public String creatingGameRemovePlayer(@PathVariable("playerNum") int playerNum, GameForm gameForm, Model model) {
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.remove(playerNum);
		
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame")
	public String processGameForm(GameForm gameForm, Map<String, Object> model, BindingResult result) {
		Game savedGame;
		try {
			savedGame = gameService.setUpNewGame(gameForm);
			savedGame = gameService.setUpNewGamePlayers(gameForm.getUsers(), savedGame);
			savedGame = gameService.setProperties(savedGame);
			savedGame = gameService.saveGame(savedGame);
			gameService.setTiles(savedGame); // Caution: Saves new tiles directly to database
			
		} catch (InvalidNumberOfPLayersException e) {
			result.rejectValue("Users[0]", "Not enough players", "There are not enough players to start");
			return VIEWS_NEW_GAME;
		}
		
		return "redirect:/game/" + savedGame.getId();
	}
	
	@GetMapping(value = "/game/{gameId}")
	public String loadGame(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		model = gameService.setupGameModel(model, gameId, auth);
		return GAME_MAIN;
	} 
	
	@PostMapping(value = "/game/{gameId}/tileAction")
	public String evalTurnAction(@PathVariable("gameId") int gameId, Boolean formValue, Authentication auth, Model model) throws Exception {
		Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
		
		if(lastTurn != null) {
			turnService.evaluateTurnAction(lastTurn, formValue);
		}
		
		return "redirect:/game/" + gameId;
	} 
	
	@GetMapping(value = "/game/{gameId}/endTurn")
	public String endTurn(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		gameService.evaluateTurn(model, gameId, auth);
		return "redirect:/game/" + gameId;
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
