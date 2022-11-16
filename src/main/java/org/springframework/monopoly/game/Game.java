package org.springframework.monopoly.game;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.Station;
import org.springframework.monopoly.property.Street;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game extends BaseEntity {

	public Game() {
		this.date= Date.valueOf(LocalDate.now());
		this.numCasas = 32;
	}
	
    @Column(name = "date")
    protected Date date;

    @Column(name = "duration")
    protected Integer duration;

    @Column(name = "num_casas")
    protected Integer numCasas;
    
    @OneToMany(mappedBy = "game")
    protected Set<Player> players;
    
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    protected Set<Company> companies;
    
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    protected Set<Station> stations;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    protected Set<Street> streets;


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(id, other.id);
	}
    
    
    
}