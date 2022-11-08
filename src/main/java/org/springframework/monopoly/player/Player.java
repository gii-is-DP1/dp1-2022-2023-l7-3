package org.springframework.monopoly.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.monopolyUser.MonopolyUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Player extends BaseEntity {

	@Column(name = "money")
	protected Integer money;

	@Column(name = "piece")
	@Enumerated(EnumType.STRING)
	protected pieceColors piece;

	@Column(name = "tile")
	protected Integer tile;

	@Column(name = "has_exit_gate")
	protected Boolean hasExitGate;

	@Column(name = "is_jailed")
	protected Boolean isJailed;

	@Column(name = "is_winner")
	protected Boolean isWinner;

	@ManyToOne
	@JoinColumn(name = "monopoly_user_id")
	private MonopolyUser monopolyUser;

}