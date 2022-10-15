package org.springframework.monopoly.tile;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@Table(name = "tiles")
public class Tile extends BaseEntity {
	
	private Integer id = this.getId();

}
