package org.springframework.monopoly.user;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "username")
	@NotEmpty
	protected String username;

	@Column(name = "password")
	@NotEmpty
	protected String password;
	
	boolean enabled;
	
	//@Column(name = "is_admin", columnDefinition = "tinyint default 0")
	//protected Boolean isAdmin = false;
	@Column(name = "is_admin", columnDefinition = "varchar(30) default 'user'")
	protected String is_admin;
	
	@OneToMany(mappedBy = "user")
	protected Set<Player> player;
	
}