package org.springframework.monopoly.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private static final String VIEWS_LOGIN = "authentication/login";
	/*
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return VIEWS_LOGIN;
	}
	*/
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return VIEWS_LOGIN;
    }
	
}
