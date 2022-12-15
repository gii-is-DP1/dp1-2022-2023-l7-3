package org.springframework.monopoly.property;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "auctions")
public class Auction extends BaseEntity {
	
	@Column(name="player_index")
	private Integer playerIndex;
	
	@ElementCollection
	private List<Integer> remainingPlayers;
	
	@Column(name="current_bid")
	private Integer currentBid;
	
	@Column(name="player_bid")
	private Integer playerBid;
	
	@Column(name="property_id")
	private Integer propertyId;
	
	@Column(name="game_id")
	private Integer gameId;
	
	public Auction() {
		
	}
	
	public Auction(Integer playerIndex, List<Integer> remainingPlayers, Integer currentBid, Integer playerBid, Integer property, Integer gameId) {
		this.playerIndex = playerIndex;
		this.remainingPlayers = remainingPlayers;
		this.currentBid = currentBid;
		this.playerBid = playerBid;
		this.propertyId = property;
		this.gameId = gameId;
	}
	
}
