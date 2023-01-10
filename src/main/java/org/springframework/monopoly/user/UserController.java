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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private static final String VIEWS_EDIT_USER = "users/editProfile";
	private final UserService monopolyUserService;

	@Autowired
	public UserController(UserService monopolyUserService) {
		this.monopolyUserService = monopolyUserService;
	} 
		
	@GetMapping(value = "/monopolyUsers/list")
    public String showMonopolyUsersListing(@RequestParam Map<String, Object> params, Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())) : 0;
		String username = params.get("username") != null ? params.get("username").toString() : "";
		model.addAttribute("currentPage", page);
		Page<User> pageUser = null;
		if (username.isEmpty()) {
			pageUser = monopolyUserService.getAll(PageRequest.of(page, 5));
			model.addAttribute("username", "");
		} else {
			pageUser = monopolyUserService.findAllWithUsername(username, PageRequest.of(page, 5));
			model.addAttribute("username", username);
		}
		
		model.addAttribute("monopolyUsers", pageUser.getContent());
		int totalPages = pageUser.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if(totalPages > 0) {
			List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
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
	

	@RequestMapping(value = "/monopolyUsers/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		monopolyUserService.delete(id);
		return "redirect:/monopolyUsers/list";
	}
	
	@GetMapping(value = "/users/byUsername/{username}")
    public String getEditUserByUsername(@PathVariable(name = "username") String username, Model model) {
		User user = monopolyUserService.findUserByName(username).orElse(null);
		
		if(user == null) {
			return "redirect:/";
		} else {
			return "redirect:/users/" + user.getId();
		}
	}
	
	@GetMapping(value = "/users/{userId}")
    public String getEditUser(@PathVariable(name = "userId") Integer id, Model model) {
        User user = monopolyUserService.findUser(id).get();
        List<Player> winnerPlayers = new ArrayList<Player>();// user.getPlayer().stream().filter(p -> p.getIsWinner()).collect(Collectors.toList());
        Set<Player> gamesList = user.getPlayer();
        Integer hoursPlayedSum = 0;

        for(Player p : user.getPlayer()) {
            if(p.getIsWinner() != null && p.getIsWinner()) { 
                winnerPlayers.add(p);
            } 

            if(p.getGame().getDuration() != null) {
                hoursPlayedSum += p.getGame().getDuration();
            }

        }

        String hoursPlayed = "";
        hoursPlayed += hoursPlayedSum/60;
        hoursPlayed += ":" + hoursPlayedSum%60;

        model.addAttribute("gamesWon", winnerPlayers.size());
        model.addAttribute("gamesPlayed", gamesList.size());
        model.addAttribute("hoursPlayed", hoursPlayed);
        model.addAttribute("user", user);
        return VIEWS_EDIT_USER;
    }
	
	@PostMapping(value = "/users/{userId}")
	public String postEditUser(@PathVariable(name = "userId") Integer id, User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEWS_EDIT_USER;
		}else {
			user.setId(id);
			this.monopolyUserService.saveUser(user);
			return "redirect:/monopolyUsers/list";
		}
		
	}
	

}
