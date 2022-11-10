package org.springframework.monopoly.tile;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GenericRepository extends CrudRepository<Generic, Integer> {
	
	List<Generic> findAll();
}
