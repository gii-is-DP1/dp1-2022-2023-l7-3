package org.springframework.samples.petclinic.tile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "taxes")
public class Taxes extends Tile {
	
	@Column(name = "price")
	private Integer price;
}
