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
	@Enumerated(EnumType.STRING)
	private Color color;
	
	@Column(name = "building_price")
	private Integer buildingPrice;
	
	@Column(name = "house_num")
	private Integer houseNum;
	
	@Column(name = "have_hotel")
	private Boolean haveHotel;
	
	@Column(name = "rental_1_house")
	private Integer rental1House;

	@Column(name = "rental_2_house")
	private Integer rental2House;

	@Column(name = "rental_3_house")
	private Integer rental3House;

	@Column(name = "rental_4_house")
	private Integer rental4House;

	@Column(name = "rental_hotel")
	private Integer rentalHotel;
	
}



