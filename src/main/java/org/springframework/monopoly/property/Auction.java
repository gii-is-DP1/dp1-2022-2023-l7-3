package org.springframework.monopoly.property;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auction {
	
	private Integer playerIndex;
	private List<Integer> remainingPlayers;
	private Integer currentBid;
	private Integer playerBid;
	private Integer propertyId;
	
	public Auction(Integer playerIndex, List<Integer> remainingPlayers, Integer currentBid, Integer playerBid, Integer property) {
		this.playerIndex = playerIndex;
		this.remainingPlayers = remainingPlayers;
		this.currentBid = currentBid;
		this.playerBid = playerBid;
		this.propertyId = property;
	}
	
}
