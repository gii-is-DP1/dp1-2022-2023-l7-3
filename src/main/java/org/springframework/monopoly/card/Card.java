package org.springframework.monopoly.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.turn.Action;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card extends BaseEntity{
	
	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column(name = "action")
	@Enumerated(EnumType.STRING)
	private Action action;
	
	private Integer quantity;	
}
