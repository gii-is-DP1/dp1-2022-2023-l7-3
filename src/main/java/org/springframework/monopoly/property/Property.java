package org.springframework.monopoly.property;


import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.springframework.monopoly.player.Player;
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

	@Column(name = "rental_1_house")
	@NotBlank
	private Integer rental1House;

	@Column(name = "rental_2_house")
	@NotBlank
	private Integer rental2House;

	@Column(name = "rental_3_house")
	@NotBlank
	private Integer rental3House;

	@Column(name = "rental_4_house")
	@NotBlank
	private Integer rental4House;

	@Column(name = "rental_hotel")
	@NotBlank
	private Integer rentalHotel;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private Player owner;
	
}
