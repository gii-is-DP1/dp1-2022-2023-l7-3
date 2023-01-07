package org.springframework.monopoly.player;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.Station;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player extends BaseEntity {

	@Column(name = "money")
	protected Integer money = 1500;

	@Column(name = "piece")
	@Enumerated(EnumType.STRING)
	protected PieceColors piece;

	@Column(name = "tile")
	protected Integer tile = 0;

	@Column(name = "has_exit_gate")
	protected Boolean hasExitGate = false;

	@Column(name = "is_jailed")
	protected Boolean isJailed = false;

	@Column(name = "is_winner")
	protected Boolean isWinner = false;

	@Column(name = "turn_order")
	protected Integer turnOrder;
	
	@Column(name = "is_bankrupcy")
	protected Boolean is_bankrupcy = false;
	
	@OneToMany(mappedBy = "player")
    protected Set<Turn> turns;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    protected Set<Company> companies;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    protected Set<Station> stations;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    protected Set<Street> streets;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;
	
	@ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
}