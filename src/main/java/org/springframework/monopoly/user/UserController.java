/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.monopoly.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {
	
	private static final String VIEWS_USER_CREATE_FORM = "authentication/signUp";
	private static final String VIEWS_USERS_LISTING = "users/monopolyUsersList";

	private final UserService monopolyUserService;

	@Autowired
	public UserController(UserService monopolyUserService) {
		this.monopolyUserService = monopolyUserService;
	}
	
//	@GetMapping("/monopolyUsers/list")
//    public ModelAndView showMonopolyUsersListing() {
//        ModelAndView result=new ModelAndView(VIEWS_USERS_LISTING);
//        result.addObject("monopolyUsers", monopolyUserService.findAll());
//        return result;
//	}
	
	@GetMapping("/monopolyUsers/list")
    public String showMonopolyUsersListing(Model model, @Param("username") String username) {
		
		if (username.isEmpty()) {
			model.addAttribute("monopolyUsers", monopolyUserService.findAll());
			model.addAttribute("username", "");
			
		} else {
			List<User> ls = monopolyUserService.findAllWithUsername(username);
			model.addAttribute("monopolyUsers", ls);
			model.addAttribute("username", username);
		}
		
        return VIEWS_USERS_LISTING;
	}
		
	@GetMapping(value = "/monopolyUsers/new")
	public String initCreationForm(Map<String, Object> model) {
		User monopolyUser = new User();
		model.put("user", monopolyUser);
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/monopolyUsers/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			//creating owner, user, and authority
			this.monopolyUserService.saveUser(user);
			return "redirect:/";
		}
	}

}
