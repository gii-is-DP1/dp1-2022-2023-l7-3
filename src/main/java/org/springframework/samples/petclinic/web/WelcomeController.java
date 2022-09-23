package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
		
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  List<Person> persons = new ArrayList<Person>();
		  Person person = new Person();
		  person.setFirstName("Juan Carlos");
		  person.setLastName("Morato");
		  persons.add(person);
		  person.setFirstName("Olegario");
		  person.setLastName("Morato");
		  persons.add(person);
		  person.setFirstName("Úrsula");
		  person.setLastName("Garrucho");
		  persons.add(person);
		  person.setFirstName("María");
		  person.setLastName("Márquez");
		  persons.add(person);
		  person.setFirstName("Miguel Ángel");
		  person.setLastName("Roldan");
		  persons.add(person);
		  person.setFirstName("Álvaro");
		  person.setLastName("Urijo");
		  persons.add(person);
		  model.put("persons", persons);
		  model.put("title", "Monopoly");
		  model.put("group", "dp1-2022-2023-l7-3");
	    return "welcome";
	  }
}
