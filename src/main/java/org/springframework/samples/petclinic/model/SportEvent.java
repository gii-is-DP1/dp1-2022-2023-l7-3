package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SportEvent {
	Integer id;
	SportEventData data;
	List<Odd> odds;	
	
	public SportEvent() {
		odds=new ArrayList<>();
	}
}
