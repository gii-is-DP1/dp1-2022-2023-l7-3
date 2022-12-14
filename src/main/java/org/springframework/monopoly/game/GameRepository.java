package org.springframework.monopoly.game;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.property.Auction;


public interface GameRepository extends CrudRepository<Game, Integer> {

	@Query("SELECT g FROM Game g WHERE g.id = :idparam")
	Game findGameById(@Param("idparam")Integer id);

	@Query("SELECT p FROM Player p WHERE p.game.id =:gameId ORDER BY p.turnOrder")
	List<Player> findPlayersOrderByTurn(@Param("gameId") Integer gameId);
	
	List<Game> findAll();
	
	@Query(nativeQuery=true, value = "SELECT * FROM game g JOIN player p ON g.id = p.game_id WHERE p.user_id = :userId")
	Page<Game> findUserGames(@Param("userId") Integer userId, Pageable pageable);
	Page<Game> findAll(Pageable pageable);
	
	@Query(nativeQuery = true,
           value = "SELECT TOP 1 g.id FROM Game g ORDER BY g.id DESC")
	Integer findLastId();
	
	@Query("SELECT a FROM Auction a WHERE a.gameId = :gameId")
	List<Auction> findLastAuction(@Param("gameId") Integer gameId);
}
