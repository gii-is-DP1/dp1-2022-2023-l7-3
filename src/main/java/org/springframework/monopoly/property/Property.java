package org.springframework.monopoly.property;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.springframework.monopoly.tile.Tile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
public class Property extends Tile {
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "price")
	@NotBlank
	private Integer price;

	@Column(name = "rental_price")
	@NotBlank
	private Integer rentalPrice;

	@Column(name = "mortage_price")
	@NotBlank
	private Integer mortagePrice;

	@Column(name = "is_mortage")
	@NotBlank
	private Boolean isMortage;
	
	@Column(name = "badge_Image")
	@NotBlank
	private String badgeImage;
	
}