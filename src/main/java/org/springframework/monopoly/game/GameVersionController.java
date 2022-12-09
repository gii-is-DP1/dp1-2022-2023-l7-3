package org.springframework.monopoly.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game/{gameId}/version")
public class GameVersionController {

	private GameService gameService;
	
	@Autowired
	public GameVersionController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Integer getVersion(@PathVariable("gameId") Integer gameId) {
		Game version = gameService.findGame(gameId).orElse(null);
		return version == null ? 0 : version.getVersion();
	}
	
}
