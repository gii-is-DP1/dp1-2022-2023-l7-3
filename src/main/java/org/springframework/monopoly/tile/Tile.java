package org.springframework.monopoly.tile;

import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;

import org.springframework.monopoly.game.Game;


@MappedSuperclass
@IdClass(TileId.class)
public abstract class Tile {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@MapsId("game")
	@JoinColumn(name = "game")
	@ManyToOne(fetch = FetchType.EAGER)
	protected Game game;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isNew() {
		return this.id == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return Objects.equals(game, other.game) && Objects.equals(id, other.id);
	}
	
	
	
}
