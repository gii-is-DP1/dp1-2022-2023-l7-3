package org.springframework.monopoly.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.monopoly.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
		
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  List<Person> persons = new ArrayList<Person>();
		  Person person = new Person();
		  Person person2 = new Person();
		  Person person3 = new Person();
		  Person person4 = new Person();
		  Person person5 = new Person();
		  Person person6 = new Person();
		  person.setFirstName("Juan Carlos");
		  person.setLastName(" Morato");
		  persons.add(person);
		  person2.setFirstName("Olegario");
		  person2.setLastName(" Morato");
		  persons.add(person2);
		  person3.setFirstName("Úrsula");
		  person3.setLastName(" Garrucho");
		  persons.add(person3);
		  person4.setFirstName("María");
		  person4.setLastName(" Márquez");
		  persons.add(person4);
		  person5.setFirstName("Miguel Ángel");
		  person5.setLastName(" Roldan");
		  persons.add(person5);
		  person6.setFirstName("Álvaro");
		  person6.setLastName(" Urquijo");
		  persons.add(person6);
		  model.put("persons", persons);
		  model.put("title", "Monopoly");
		  model.put("group", "dp1-2022-2023-l7-3");
	    return "welcome";
	  }
}
