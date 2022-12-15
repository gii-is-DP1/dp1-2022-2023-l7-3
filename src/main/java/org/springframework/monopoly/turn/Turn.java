package org.springframework.monopoly.turn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turns")
public class Turn extends BaseEntity {

	@Column(name = "action")
	@Enumerated(EnumType.STRING)
	private Action action = Action.NOTHING_HAPPENS;
	
	@Column(name = "is_action_evaluated")
	private Boolean isActionEvaluated = false;
	
	@Column(name = "is_auction_ongoing")
	private Boolean isAuctionOnGoing = false;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "turn_number")
	private Integer turnNumber;
	
	@Column(name = "roll")
	@NotNull
	private Integer roll;
	
	@Column(name = "is_doubles")
	private Boolean isDoubles = false;
	
	@Column(name = "is_finished")
	private Boolean isFinished = false;
	
	@Column(name = "actionCardId")
	private Integer actionCardId = null;
	
	// Relations
	
	@Column(name = "initial_tile_id")
	private Integer initial_tile;
	
	public Integer getFinalTile() {
		return getInitial_tile() + getRoll();
	}
	
	@ManyToOne
	@JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private Player player;
	
	
}
