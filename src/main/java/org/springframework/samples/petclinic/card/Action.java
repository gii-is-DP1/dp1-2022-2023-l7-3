package org.springframework.samples.petclinic.card;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.monopoly.model.NamedEntity;

@Entity
@Table(name = "actions")
public class Action extends NamedEntity {

}
