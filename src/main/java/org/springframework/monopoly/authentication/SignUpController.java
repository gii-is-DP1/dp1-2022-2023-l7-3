package org.springframework.monopoly.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {
	
	private static final String VIEWS_SIGN_UP = "authentication/signUp";
	
	@Autowired
	public SignUpController() {
		
	}
	
	@GetMapping(value = "/signup")   
	public String signUp(Map<String, Object> model) {
		return VIEWS_SIGN_UP;
	}
	
}
