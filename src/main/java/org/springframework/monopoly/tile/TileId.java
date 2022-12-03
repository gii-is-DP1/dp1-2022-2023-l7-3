package org.springframework.monopoly.tile;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TileId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4159228973454437019L;
	private Integer id;
	private Integer game;
	
	public TileId() {
		
	}
	
	public TileId(Integer id, Integer game) {
		this.id = id;
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
		TileId other = (TileId) obj;
		return Objects.equals(game, other.game) && Objects.equals(id, other.id);
	}
	
	
	
}
