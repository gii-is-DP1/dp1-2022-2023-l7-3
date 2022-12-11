package org.springframework.monopoly.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "streets")
public class Street extends Property{
	
	@Column(name = "color")
	@Enumerated(EnumType.STRING)
	@NotNull
	private Color color;
	
	@Column(name = "building_price")
	@NotNull
	private Integer buildingPrice;
	
	@Column(name = "house_num")
	@Max(value=4,message="The number for Houses is between 0 and 4")
	@Min(value=0,message="The number for Houses is between 0 and 4")
	private Integer houseNum;
	
	@Column(name = "have_hotel")
	@NotNull
	private Boolean haveHotel;
	
	@Column(name = "rental_1_house")
	@NotNull
	private Integer rental1House;

	@Column(name = "rental_2_house")
	@NotNull
	private Integer rental2House;

	@Column(name = "rental_3_house")
	@NotNull
	private Integer rental3House;

	@Column(name = "rental_4_house")
	@NotNull
	private Integer rental4House;

	@Column(name = "rental_hotel")
	@NotNull
	private Integer rentalHotel;
	
}



