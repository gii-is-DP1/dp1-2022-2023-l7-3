package org.springframework.monopoly.tile;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.springframework.monopoly.game.Game;


@MappedSuperclass
@IdClass(TileId.class)
public abstract class Tile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}
	
}
