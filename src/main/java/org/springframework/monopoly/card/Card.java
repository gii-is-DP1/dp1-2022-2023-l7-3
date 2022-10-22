package org.springframework.monopoly.card;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;
import org.springframework.monopoly.tile.CommunityBox;
import org.springframework.monopoly.tile.Luck;
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
	
	// Relations
	
	@OneToMany
	@JoinColumn(name = "luck_id")
	private Collection<Luck> luck;
	
	@OneToMany
	@JoinColumn(name = "community_box_id")
	private Collection<CommunityBox> communityBox;
}
