package org.springframework.monopoly.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
		
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  model.put("title", "Monopoly");
		  model.put("group", "dp1-2022-2023-l7-3");
		  model.put("screenTittle", "Screen Tittle");
	    return "welcome";
	  }
}
