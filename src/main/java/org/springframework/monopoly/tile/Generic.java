package org.springframework.monopoly.tile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "generics")

public class Generic extends Tile {
	
	@Column(name = "generic_type")
	@Enumerated(EnumType.STRING)
	private GenericType genericType;
}
