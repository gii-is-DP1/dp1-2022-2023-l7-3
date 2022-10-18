package org.springframework.samples.petclinic.tile;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tiles")
public class Tile extends BaseEntity {


}
