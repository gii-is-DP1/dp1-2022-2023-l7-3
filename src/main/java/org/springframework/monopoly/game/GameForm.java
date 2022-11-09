package org.springframework.monopoly.game;

import java.util.List;

import org.springframework.monopoly.player.Player;

public class GameForm {

	private Game game;
	private List<Player> players;
	
	public GameForm() {
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game gameId) {
		this.game = gameId;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
