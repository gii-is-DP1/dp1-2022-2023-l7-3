package org.springframework.monopoly.tile;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LuckRepository extends CrudRepository<Luck, Integer>{
	
	@Query("SELECT luck FROM Luck luck WHERE luck.game.id = :id")
	List<Luck> findAllLuckByGameId(@Param("id") Integer id);
	
	@Query("SELECT luck FROM Luck luck WHERE luck.game.id = :id AND luck.id = :luckId")
	Luck findLuckByGameId(@Param("id") Integer id, @Param("luckId") Integer luckId);
}
