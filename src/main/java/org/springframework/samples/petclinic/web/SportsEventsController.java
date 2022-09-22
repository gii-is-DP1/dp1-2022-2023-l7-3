package org.springframework.samples.petclinic.web;



import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Odd;
import org.springframework.samples.petclinic.model.SportEvent;
import org.springframework.samples.petclinic.service.SportEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportsEventsController {

	@Autowired
	SportEventService eventService;
	
	@GetMapping(path="/api/sportEvents/{id}")
	public SportEvent getEventById(@PathVariable("id") Integer id) {		
		SportEvent event=eventService.getEventById(id);
		if(event==null)
			throw new ResourceNotFoundException("No sport event found"
													+ " with id:"+id);
		return event; 
	}
	
	@PostMapping(path="/api/sportEvents/{id}/odds")
	public ResponseEntity<Odd> addOddToEvent(@PathVariable("id") Integer id, @RequestBody Odd  odd) {
		eventService.addOddToEvent(id, odd);
		return new ResponseEntity<>(odd,HttpStatus.CREATED);
	}
}
