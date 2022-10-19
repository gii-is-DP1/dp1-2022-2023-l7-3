package org.springframework.samples.petclinic.tile;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.springframework.monopoly.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class Tile extends BaseEntity {


}
