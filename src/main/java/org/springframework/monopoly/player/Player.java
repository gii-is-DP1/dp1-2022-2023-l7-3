package org.springframework.monopoly.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.monopoly.model.BaseEntity;


@Entity
public class Player extends BaseEntity {

	@Column(name = "username")
	@NotEmpty
	protected String username;

	@Column(name = "password")
	@NotEmpty
	protected String password;
	
	@Column(name = "isAdmin")
	protected Boolean isAdmin = false;
	
	@Column(name = "money")
	protected Integer money;
	
	@Column(name = "piece")
	protected pieceColors piece;
	
	@Column(name = "tile")
	protected Integer tile;
	
	@Column(name = "hasExitGate")
	protected Boolean hasExitGate;
	
	@Column(name = "isJailed")
	protected Boolean isJailed;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public pieceColors getPiece() {
		return piece;
	}

	public void setPiece(pieceColors piece) {
		this.piece = piece;
	}

	public Integer getTile() {
		return tile;
	}

	public void setTile(Integer tile) {
		this.tile = tile;
	}

	public Boolean getHasExitGate() {
		return hasExitGate;
	}

	public void setHasExitGate(Boolean hasExitGate) {
		this.hasExitGate = hasExitGate;
	}

	public Boolean getIsJailed() {
		return isJailed;
	}

	public void setIsJailed(Boolean isJailed) {
		this.isJailed = isJailed;
	}

	

}
