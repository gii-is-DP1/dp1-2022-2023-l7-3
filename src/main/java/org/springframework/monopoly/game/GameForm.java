package org.springframework.monopoly.game;

import java.util.ArrayList;
import java.util.List;

public class GameForm {

	private List<Integer> users = new ArrayList<Integer>();
	
	public GameForm() {
		
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}
	
}
