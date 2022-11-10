package org.springframework.monopoly.game;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.monopolyUser.MonopolyUserService;
import org.springframework.monopoly.player.PieceColors;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
	
	private static final String VIEWS_NEW_GAME = "game/newGame";
	private static final String GAME_MAIN = "game/main";
	
	private GameService gameService;
	private PlayerService playerService;
	private MonopolyUserService userService;
	
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, MonopolyUserService userService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
	}
	
	@GetMapping(value = "/newGame")
	public String newGame(Map<String, Object> model, Authentication authentication) {
		Game newGame = new Game(); 
		List<Player> players = playerService.findAll();
		Player creator = playerService.findPlayerByUserId(userService.findUserByName(authentication.getName()).getId());
		
		model.put("game", newGame);
		model.put("creator", creator);
		model.put("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame")
	public String processGameForm(GameForm gameForm, Map<String, Object> model) {
		Game game = gameForm.getGame();
		List<Player> players = gameForm.getPlayers();
		List<PieceColors> colors = playerService.getAllPieceTypes();
		Collections.shuffle(colors);
		List<Integer> turns = Stream.iterate(0, i-> i<players.size(), i -> ++i).collect(Collectors.toList());
		Collections.shuffle(turns);
		int i = 0;
		for(Player p : players) {
			p.setIsJailed(false);
			p.setTile(0);
			p.setMoney(1500);
			p.setPiece(colors.get(i++));
			p.setHasExitGate(false);
			p.setTurn_number(turns.get(i));
			
		}
		game.setPlayers(new HashSet<Player>(players));
		gameService.saveGame(game);
		return GAME_MAIN;
	}
}
