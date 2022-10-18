package org.springframework.monopoly.tile;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "community_box")
public class CommunityBox extends Tile{

}
