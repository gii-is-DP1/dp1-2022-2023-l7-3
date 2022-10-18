package org.springframework.monopoly.tile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Community_box")
public class Generic extends Tile {
	
	@Column(name = "generic_type")
	private GenericType genericType;
}
