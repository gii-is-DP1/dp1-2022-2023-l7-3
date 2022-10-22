package org.springframework.monopoly.turn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.monopoly.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turns")
public class Turn extends BaseEntity{
	
	@Column(name= "action")
	@Enumerated(EnumType.STRING)
	private actionCard action;
	
	public enum actionCard {
		PAY, PAY_PLAYERS, CHARGE, CHARGE_PLAYERS, MOVE 
	}
	
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer turnNumber;
	
	@NotNull
	private Integer roll;
	
//	@ManyToOne
//	@JoinColumn(name = "final_box_id")
//	private Box initial_box;
//
//	@ManyToOne(optional= false)
//	@JoinColumn(name = "final_box_id")
//	private Box final_box;
	
}
