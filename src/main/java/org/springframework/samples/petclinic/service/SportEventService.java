package org.springframework.samples.petclinic.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.samples.petclinic.model.Odd;
import org.springframework.samples.petclinic.model.SportEvent;
import org.springframework.samples.petclinic.model.SportEventData;
import org.springframework.stereotype.Service;

@Service
public class SportEventService {
	Map<Integer,SportEvent> events;
	
	public SportEventService() {
		events=new HashMap<>();
		SportEvent event=new SportEvent();
		SportEventData data=new SportEventData();
		
		data.setLeagueId(1);
		data.setHomeTeam("Betis");
		data.setVisitingTeam("Sevilla");
		
		Odd odd1=new Odd();
		odd1.setName("Betis gana");
		odd1.setPrice("1.35");
		
		Odd odd2=new Odd();
		odd2.setName("Betis pierde");
		odd2.setPrice("1.25");
		
		event.setId(new Integer(390));
		event.setData(data);
		event.getOdds().add(odd1);
		event.getOdds().add(odd2);
		events.put(event.getId(), event);
	}
	
	public SportEvent getEventById(Integer id) {
		return events.get(id);
	}
	
	public void addOddToEvent(Integer eventId, Odd odd) throws ResourceNotFoundException {
		SportEvent event=getEventById(eventId);
		if(event==null)
			throw new ResourceNotFoundException("No sport event found with id:"+eventId);
		event.getOdds().add(odd);
	}
}
