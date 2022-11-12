package org.springframework.monopoly.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.user.User;

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
	protected PieceColors piece;

	@Column(name = "tile")
	protected Integer tile;

	@Column(name = "has_exit_gate")
	protected Boolean hasExitGate;

	@Column(name = "is_jailed")
	protected Boolean isJailed;

	@Column(name = "is_winner")
	protected Boolean isWinner;

	@Column(name = "turn_number")
	protected Integer turn_number;
	
	@OneToMany(mappedBy = "Property")
	private List<Property> properties;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}