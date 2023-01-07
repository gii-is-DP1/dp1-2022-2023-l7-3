package org.springframework.monopoly.property;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreetForm {
	
	private Integer streetId;
	@Max(value=4)
	@Min(value=0)
	private Integer house;
	private Boolean hotel;

	
	public StreetForm(Integer streetId, Integer house, Boolean hotel){
		this.streetId=streetId;
		this.house=house;
		this.hotel=hotel;
		
		
	}

}
