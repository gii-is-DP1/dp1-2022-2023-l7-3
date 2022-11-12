package org.springframework.monopoly.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.monopoly.player.Player;


public interface GameRepository extends CrudRepository<Game, Integer> {

	@Query(nativeQuery = true,
			value = "SELECT * FROM "
			+ "(SELECT players from game where id = :gameId) "
			+ "ORDER BY turn_number")
	List<Player> findPlayersOrderByTurn(@Param("gameId") Integer gameId);
}
