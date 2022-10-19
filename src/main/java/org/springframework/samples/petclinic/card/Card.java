package org.springframework.samples.petclinic.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "card_type_id")
	private CardType cardType;
	
	@ManyToOne
	@JoinColumn(name = "action_id")
	private Action action;
	
	private Integer quantity;	
}
