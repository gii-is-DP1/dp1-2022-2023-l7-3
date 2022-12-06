package org.springframework.monopoly.property;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuctionForm {

	private Integer playerIndex;
	private List<Integer> remainingPlayers;
	private Integer currentBid;
	private Integer playerBid;
	private Integer propertyId;

	
}
