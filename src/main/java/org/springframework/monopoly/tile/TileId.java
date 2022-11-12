package org.springframework.monopoly.tile;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.monopoly.game.Game;

public class TileId implements Serializable {
	
	private Integer id;
	private Game game;
	
	public TileId() {
		
	}
	
	public TileId(Integer id, Game game) {
		super();
		this.id = id;
		this.game = game;
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
		TileId other = (TileId) obj;
		return Objects.equals(game, other.game) && Objects.equals(id, other.id);
	}
	
	
	
}
