package org.springframework.monopoly.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.PieceColors;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
	
	private static final String VIEWS_NEW_GAME = "game/newGame";
	private static final String GAME_MAIN = "game/main";
	private static final String BLANK_GAME = "game/blankGame"; //este es provisional para los tags

	
	private GameService gameService;
	private PlayerService playerService;
	private UserService userService;
	
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, UserService userService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
	}
	
//	@InitBinder("Player")
//	public void initPlayerBinder(WebDataBinder dataBinder) {
//		
//	}
//	@InitBinder("User")
//	public void initUserBinder(WebDataBinder dataBinder) {
//		
//	}
//	@InitBinder("Game")
//	public void initGameFormBinder(WebDataBinder dataBinder) {
//		
//	}

	//PROVISIONAL
	@GetMapping(value = "/blankGame")
	public String blankGame(Map<String, Object> model, Authentication authentication) {
		return BLANK_GAME;
	}
	
	@GetMapping(value = "/newGame")
	public String newGame(Map<String, Object> model, Authentication authentication) {
//		Game newGame = new Game(); 
		List<User> users = userService.findAll();
		
		User creatorUser = userService.findUserByName(authentication.getName());
		users.remove(creatorUser);
		
		List<User> players = new ArrayList<User>();
		players.add(creatorUser);
		
//		model.put("game", newGame);
//		model.put("creator", creator);
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
			p.setTurn_number(turns.get(i++));
			
			playerService.savePlayer(p);
			players.add(p);
		}
		
		game.setPlayers(new HashSet<Player>());
		gameService.saveGame(game);
		return "/welcome";//GAME_MAIN;
	}
}
