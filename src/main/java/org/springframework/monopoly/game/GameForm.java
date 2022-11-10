package org.springframework.monopoly.game;

import java.util.List;

import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.user.User;

public class GameForm {

	private List<Integer> users;
	
	public GameForm() {
		
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}
	
}
