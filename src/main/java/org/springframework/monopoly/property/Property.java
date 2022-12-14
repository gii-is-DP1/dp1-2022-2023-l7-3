package org.springframework.monopoly.property;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.tile.Tile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Property extends Tile {
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "price")
	@NotNull
	private Integer price;

	@Column(name = "rental_price")
	@NotNull
	private Integer rentalPrice;

	@Column(name = "mortage_price")
	@NotNull
	private Integer mortagePrice;

	@Column(name = "is_mortage")
	@NotNull
	private Boolean isMortage;
	
	@Column(name = "badge_Image")
	@NotNull
	private String badgeImage;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
	private Player owner;

	
}
