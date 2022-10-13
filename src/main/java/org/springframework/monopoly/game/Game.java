package org.springframework.monopoly.game;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.player.Player;

@Entity
public class Game extends BaseEntity {

	@Column(name = "winner")
	protected Player winner;

	@Column(name = "date")
	@NotEmpty
	protected Date date;
	
	@Column(name = "duration")
	protected Duration duration;

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

}
