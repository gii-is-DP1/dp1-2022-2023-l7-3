package org.springframework.monopoly.monopolyUser;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MonopolyUser extends BaseEntity {

	@Column(name = "username")
	@NotEmpty
	protected String username;

	@Column(name = "Password")
	@NotEmpty
	protected String Password;
	
	@Column(name = "is_admin")
	@NotEmpty
	protected Boolean isAdmin;
	
	@OneToMany(mappedBy = "monopolyUser",
            cascade = CascadeType.ALL)
    private Set<Player> players;
}