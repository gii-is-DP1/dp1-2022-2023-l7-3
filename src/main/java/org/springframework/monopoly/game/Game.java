package org.springframework.monopoly.game;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    @Column(name = "date")
    protected Date date;

    @Column(name = "duration")
    protected Time duration;

    @NotEmpty
    @Column(name = "num_casas")
    protected Integer numCasas;

    @ManyToMany
    @JoinTable(
    		  name = "game_players", 
    		  joinColumns = @JoinColumn(name = "game_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "player_id"))
    protected Set<Player> players;
    
    @ManyToMany
    @JoinTable(
    		  name = "game_streets", 
    		  joinColumns = @JoinColumn(name = "game_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "street_id"))
    protected Set<Street> streets;
    
    @ManyToMany
    @JoinTable(
    		  name = "game_stations", 
    		  joinColumns = @JoinColumn(name = "game_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "station_id"))
    protected Set<Station> stations;
    
    @ManyToMany
    @JoinTable(
    		  name = "game_companies", 
    		  joinColumns = @JoinColumn(name = "game_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "company_id"))
    protected Set<Company> companies;
    
    
    
}