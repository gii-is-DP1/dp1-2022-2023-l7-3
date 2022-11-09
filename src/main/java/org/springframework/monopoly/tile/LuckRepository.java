package org.springframework.monopoly.tile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LuckRepository extends CrudRepository<Luck, Integer>{
	List<Luck> findAll();
}
