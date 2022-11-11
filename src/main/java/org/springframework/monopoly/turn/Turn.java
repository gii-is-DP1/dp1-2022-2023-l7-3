package org.springframework.monopoly.turn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class Turn extends BaseEntity{
	
	@ElementCollection
	@Column(name= "action")
	@Enumerated(EnumType.STRING)
	private List<Action> actions = new ArrayList<Action>();
	
	@Column(name = "quantity")
	private Integer quantity;
		
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "turn_number")
	private Integer turnNumber;
	
	@NotNull
	private Integer roll;
	
	// Relations
	
	@Column(name = "initial_tile_id")
	private Integer initial_tile;
	
	@ElementCollection
	@Column(name = "mid_tiles")
	private List<Integer> mid_tiles;

	@Column(name = "final_tile_id")
	private Integer final_tile;
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	
}
