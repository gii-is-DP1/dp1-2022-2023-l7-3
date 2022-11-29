package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenericRepository extends CrudRepository<Generic, Integer> {
	
	@Query("SELECT g FROM Generic g WHERE g.game.id = :gameId")
	List<Generic> findAll(@Param("gameId") Integer gameId);
	
	@Query("SELECT g FROM Generic g WHERE g.game.id = :gameId AND g.id = :id")
	Optional<Generic> findGenericByGameId(@Param("gameId") Integer gameId, @Param("id") Integer id);
}
