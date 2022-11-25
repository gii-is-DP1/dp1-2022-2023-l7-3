package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaxesRepository extends CrudRepository<Taxes, Integer>{
	
	@Query("SELECT t FROM Taxes t WHERE t.game.id = :gameId")
	List<Taxes> findAll(@Param("gameId") Integer gameId);
	
	@Query("SELECT t FROM Taxes t WHERE t.game.id = :gameId AND t.id = id")
	Optional<Taxes> findById(@Param("gameId") Integer gameId, @Param("id") Integer id);
}
