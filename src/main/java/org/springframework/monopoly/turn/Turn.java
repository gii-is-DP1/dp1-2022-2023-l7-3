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

	//atributo nuevo boolean de si tira doble
	//atributo para finalizado
	
	@ElementCollection
	@Column(name= "action")
	@Enumerated(EnumType.STRING)
	private List<Action> actions = new ArrayList<Action>();
	
	@ElementCollection
	@Column(name = "quantities")
	private List<Integer> quantities = new ArrayList<Integer>();
	
		
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
	private List<Integer> mid_tiles = new ArrayList<Integer>();

	@Column(name = "final_tile_id")
	private Integer final_tile;
	
	@ManyToOne
	@JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private Player player;
	
	
}
