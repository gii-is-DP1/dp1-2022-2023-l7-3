package org.springframework.monopoly.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreetForm {
	
	private Integer streetId;
	private Integer house;
	private Boolean hotel;
	public StreetForm(Integer streetId, Integer house, Boolean hotel){
		this.streetId=streetId;
		this.house=house;
		this.hotel=hotel;
	}

}
