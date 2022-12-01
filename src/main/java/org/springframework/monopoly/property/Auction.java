package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.monopoly.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auction {
	
	private Integer playerIndex;
	private List<Player> remainingPlayers;
	private Integer currentBid;
	private Integer playerBid;
	private Property property;
	
	public Auction(Integer playerIndex, List<Player> remainingPlayers, Integer currentBid, Integer playerBid, Property property) {
		this.playerIndex = playerIndex;
		this.remainingPlayers = remainingPlayers;
		this.currentBid = currentBid;
		this.playerBid = playerBid;
		this.property = property;
	}
	
}
