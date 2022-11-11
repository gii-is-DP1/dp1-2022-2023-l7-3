package org.springframework.monopoly.turn;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TurnRepository extends  CrudRepository<Turn, Integer>{
	
	@Query(nativeQuery = true,
			value = "SELECT TOP 1 * FROM turns WHERE game_id = :gameId ORDER BY turn_number DESC")
	Turn findLastTurn(@Param("gameId") Integer gameId);
	
}
