package org.springframework.monopoly.card;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.monopoly.model.NamedEntity;

@Entity
@Table(name = "card_types")
public class CardType extends NamedEntity{

}
