package org.springframework.monopoly.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "streets")
public class Street extends Property{
	
	@Column(name = "color")
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Color color;
	
	@Column(name = "building_price")
	@NotBlank
	private Integer buildingPrice;
	
	@Column(name = "house_num")
	@NotBlank
	private Integer houseNum;
	
	@Column(name = "have_hotel")
	@NotBlank
	private Boolean haveHotel;
	
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
	
}



