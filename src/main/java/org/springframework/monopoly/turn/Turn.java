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
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.Station;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.tile.CommunityBox;
import org.springframework.monopoly.tile.Generic;
import org.springframework.monopoly.tile.Luck;
import org.springframework.monopoly.tile.Taxes;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turns")
public class Turn extends BaseEntity{
	
	@Column(name= "action")
	@Enumerated(EnumType.STRING)
	private Action action;
		
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer turnNumber;
	
	@NotNull
	private Integer roll;
	
	// Relations
	
	@Column(name = "initial_tile_id")
	private Integer initial_tile;

	@Column(name = "final_tile_id")
	private Integer final_tile;
	
	@ManyToOne
	@JoinColumn(name = "community_box_id")
	private CommunityBox communityBox; 
	
	@ManyToOne
	@JoinColumn(name = "luck_id")
	private Luck luck; 
	
	@ManyToOne
	@JoinColumn(name = "taxes_id")
	private Taxes taxes; 
	
	@ManyToOne
	@JoinColumn(name = "generic_id")
	private Generic generic; 
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company; 
	
	@ManyToOne
	@JoinColumn(name = "station_id")
	private Station station;
	
	@ManyToOne
	@JoinColumn(name = "street_id")
	private Street street; 
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	
}
