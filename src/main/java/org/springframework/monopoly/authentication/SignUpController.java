package org.springframework.monopoly.authentication;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
	
	private static final String VIEWS_SIGN_UP = "authentication/signUp";
	
	private final UserService userService;

	@Autowired
	public SignUpController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/signup")
	public String signUp(Map<String, Object> model) {
		return VIEWS_SIGN_UP;
	}
	
	@PostMapping(value = "/signup")
	public String processCreationForm(@ModelAttribute("User") @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SIGN_UP;
		} else {
			
			// Temporary fix to valid annotation not adding with the unique constraint error to the binding result
			List<String> usernames = userService.findAll().stream().map(u -> u.getUsername()).collect(Collectors.toList());
			if(usernames.contains(user.getUsername())) {
				result.rejectValue("username", "ExistingUsername", "An user with that username already exists");
				return VIEWS_SIGN_UP;
			}
			
			user.setEnabled(true);
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	
	
}