package org.springframework.monopoly.turn;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TurnRepository extends  CrudRepository<Turn, Integer>{
	
	@Query(nativeQuery = true,
			value = "SELECT TOP 1 * FROM turns WHERE game_id = :gameId ORDER BY turn_number DESC")
	Optional<Turn> findLastTurn(@Param("gameId") Integer gameId);
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM turns WHERE game_id = :gameId AND player_id = :playerId ORDER BY turn_number DESC LIMIT 3")
	List<Turn> findLastJailedTurns(@Param("gameId") Integer gameId, @Param("playerId") Integer playerId);
	
}
