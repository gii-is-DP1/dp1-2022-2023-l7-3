package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GenericRepository extends CrudRepository<Generic, Integer> {
	List<Generic> findAll();
	
	Optional<Generic> findById();
	@Query("SELECT ct FROM CardType ct")
	List<GenericType> findAllTypes();
}
