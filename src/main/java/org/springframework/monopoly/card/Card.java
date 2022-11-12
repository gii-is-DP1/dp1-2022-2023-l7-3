package org.springframework.monopoly.card;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "badge_image")
	@NotBlank
	private String badgeImage;
	
	// Relations
	
    @ManyToMany
    @JoinTable(
    		  name = "card_luck", 
    		  joinColumns = @JoinColumn(name = "card_id"), 
    		  inverseJoinColumns = {
    				  @JoinColumn(name = "luck_id"),
    				  @JoinColumn(name = "game_id")
    				  })
    protected Set<Luck> lucks;
	
	@ManyToMany
    @JoinTable(
  		  name = "card_community_box", 
  		  joinColumns = @JoinColumn(name = "card_id"), 
  		  inverseJoinColumns = {
  				  @JoinColumn(name = "community_box_id"),
				  @JoinColumn(name = "game_id")
				  })
  protected Set<CommunityBox> community_box;
}
