package org.springframework.monopoly.player;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.monopolyUser.MonopolyUser;
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.Station;
import org.springframework.monopoly.property.Street;

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

	@ManyToMany
	@JoinTable(name = "player_streets", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "street_id"))
	protected Set<Street> streets;

	@ManyToMany
	@JoinTable(name = "player_stations", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "station_id"))
	protected Set<Station> stations;

	@ManyToMany
	@JoinTable(name = "player_companies", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	protected Set<Company> companies;

}