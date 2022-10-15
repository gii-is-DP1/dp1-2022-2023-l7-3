package org.springframework.monopoly.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.tile.Tile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "id" } )
@Table(name = "cards")
public class Card extends BaseEntity {
	
	private Integer id = this.getId();
	
	@Column(name = "card_type")
	private cardType cardType;
	
	@JoinColumn(name = "action")
	private action action;
	
	@JoinColumn(name = "quantity")
	private Integer quantity;
	
	@JoinColumn(name = "tile")
	private Tile tile;
	
}
