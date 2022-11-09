package org.springframework.monopoly.authentication;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.monopolyUser.MonopolyUser;
import org.springframework.monopoly.monopolyUser.MonopolyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
	
	private static final String VIEWS_SIGN_UP = "authentication/signUp";
	
	private final MonopolyUserService userService;

	@Autowired
	public SignUpController(MonopolyUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/signup")
	public String signUp(Map<String, Object> model) {
		return VIEWS_SIGN_UP;
	}
	
	@PostMapping(value = "/signup")
	public String processCreationForm(@Valid MonopolyUser user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SIGN_UP;
		} else {
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	
	
}