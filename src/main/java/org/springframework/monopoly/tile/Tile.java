package org.springframework.monopoly.tile;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.model.BaseEntity;


@MappedSuperclass
public class Tile extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;


}
