package org.springframework.monopoly.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.PieceColors;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Color;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
	
	private static final String VIEWS_NEW_GAME = "game/newGame";
	private static final String GAME_MAIN = "game/gameMain";
	private static final String BLANK_GAME = "game/blankGame"; //este es provisional para los tags
	public static final String GAMES_LISTING="game/GameList"; // Creado para listar partidas
	
	public static User currentUser;
	private static String currentUserLocation;
	
	private GameService gameService;
	private PlayerService playerService;
	private UserService userService;
	private TurnService turnService;
	private StreetService streetService;
	
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, UserService userService, TurnService turnService,
			StreetService streetService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
		this.turnService = turnService;
		this.streetService = streetService;
	}

	//PROVISIONAL
	@GetMapping(value = "/blankGame")
	public String blankGame(Map<String, Object> model, Authentication authentication) {
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
	public String processGameForm(GameForm gameForm, Map<String, Object> model) {
		Game game = new Game();
		System.out.println(game.getNumCasas());
		List<Integer> userIds = gameForm.getUsers();
		List<User> users = new ArrayList<User>();
		for(Integer i:userIds) {
			users.add(userService.findUser(i).get());
		}
		
		List<PieceColors> colors = playerService.getAllPieceTypes();
		Collections.shuffle(colors);
		List<Integer> turns = Stream.iterate(0, i-> i<users.size(), i -> ++i).collect(Collectors.toList());
		Collections.shuffle(turns);
		
		List<Player> players = new ArrayList<Player>();
		int i = 0;
		for(User u : users) {
			Player p = new Player();
			p.setUser(u);
			
			p.setIsJailed(false);
			p.setTile(0);
			p.setMoney(1500);
			p.setPiece(colors.get(i));
			p.setHasExitGate(false);
			p.setTurnOrder(turns.get(i++));
			p.setGame(game);
			
			playerService.savePlayer(p);
			players.add(p);
		} 
		
		game.setPlayers(new HashSet<Player>(players));
		Game savedGame = gameService.saveGame(game);
		
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
		Optional<Turn> lastTurnOpt = turnService.findLastTurn(gameId);
		
		Turn nextTurn = new Turn();
		Player nextPlayer = null;
		
		// Calculating next turn result
		if(lastTurnOpt.isPresent()) {
			Turn lastTurn = lastTurnOpt.get();
			nextTurn.setTurnNumber(lastTurn.getTurnNumber() + 1);
			nextPlayer = players.get((players.indexOf(lastTurn.getPlayer()) + 1) % players.size());
		} else {
			nextTurn.setTurnNumber(0);
			nextPlayer = players.get(0);
		}
		
		model.addAttribute("Game", game);
		model.addAttribute("Turn", null);
		model.addAttribute("Players", players);
		
		List<List<Property>> properties = new ArrayList<List<Property>>();
		for(Player p:players) {
			properties.add(p.getProperties());
		}
		    
		model.addAttribute("Properties", properties);
		    
		// Street colors
 		List<List<Color>> colors = new ArrayList<List<Color>>();
		for(Player p:players) {
			colors.add(streetService.getStreetsColors(p.getStreets()));
		}
 		model.addAttribute("Colors", colors);
   		
		if(currentUser == null && requestUser.equals(nextPlayer.getUser())) {
			currentUser = requestUser;
			return "redirect:/game/" + gameId + "/nextTurn";
		} else {
			return GAME_MAIN;
		}
		
		
	}
	
	@GetMapping(value = "/game/{gameId}/nextTurn")
	public String calculateGameTurn(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		
		// Find all details needed to show to users
		Integer requestUserId = userService.findUserByName(auth.getName()).getId();
		
		if(currentUser.getId().equals(requestUserId)) {
			
			Optional<Game> gameOptional = gameService.findGame(gameId);
			if(gameOptional.isEmpty()) {
				throw new Exception("Game doesn't exist");
			} 
			Game game = gameOptional.get();
			
			List<Player> players = new ArrayList<Player>(game.getPlayers());
			Comparator<Player> c = Comparator.comparing(p -> p.getTurnOrder());
			Collections.sort(players, c);
			Optional<Turn> lastTurnOpt = turnService.findLastTurn(gameId);
			
			Turn nextTurn = new Turn();
			Player nextPlayer = null;
			
			// Calculating next turn result
			if(lastTurnOpt.isPresent()) {
				Turn lastTurn = lastTurnOpt.get();
				nextTurn.setTurnNumber(lastTurn.getTurnNumber() + 1);
				nextPlayer = players.get((players.indexOf(lastTurn.getPlayer()) + 1) % players.size());
			} else {
				nextTurn.setTurnNumber(0);
				nextPlayer = players.get(0);
			} 
			
			nextTurn.setGame(game);
			nextTurn.setPlayer(nextPlayer);
			nextTurn.setInitial_tile(nextPlayer.getTile());
			nextTurn = turnService.calculateTurn(nextTurn);
			
			turnService.saveTurn(nextTurn);
			
			model.addAttribute("Game", game);
			model.addAttribute("Turn", nextTurn);
			model.addAttribute("Players", game.getPlayers());
	
			return GAME_MAIN;
		} else {
			return "redirect:/game/" + gameId;
		}
	}
	
    @GetMapping("/games/list")
    public ModelAndView showGamesListing() {
        ModelAndView result=new ModelAndView(GAMES_LISTING);
        result.addObject("games", gameService.getAll());
        return result;
    }
   	
}
