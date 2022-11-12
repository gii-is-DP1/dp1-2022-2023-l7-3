package org.springframework.monopoly.game;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.property.Property;

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
    protected Time duration;

    @Column(name = "num_casas")
    protected Integer numCasas;
    
    @OneToMany(mappedBy = "game")
    protected Set<Player> players;
    
    @OneToMany(mappedBy = "properties")
    protected Set<Property> properties;
    
}