package org.springframework.monopoly.tile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommunityBoxRepository extends CrudRepository<CommunityBox, Integer> {
	
	List<CommunityBox> findAll();
}
