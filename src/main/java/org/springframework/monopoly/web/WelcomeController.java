package org.springframework.monopoly.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
		
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	 
		  List<String> authors = new ArrayList<String>();
		  authors.add("Juan Carlos Morato");
		  authors.add("Olegario Morato");
		  authors.add("Úrsula Garrucho");
		  authors.add("María Márquez");
		  authors.add("Miguel Ángel Roldan");
		  authors.add("Álvaro Urquijo");
		  model.put("authors", authors);
		  
		  model.put("title", "Monopoly");
		  model.put("group", "dp1-2022-2023-l7-3");
		  model.put("screenTittle", "Welcome");
	    return "welcome";
	  }
}
