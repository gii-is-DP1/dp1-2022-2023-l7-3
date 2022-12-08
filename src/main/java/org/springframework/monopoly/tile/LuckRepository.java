package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LuckRepository extends CrudRepository<Luck, Integer>{
	
	@Query("SELECT luck FROM Luck luck WHERE luck.game.id = :id")
	List<Luck> findAllLuckByGameId(@Param("id") Integer id);
	
	@Query("SELECT luck FROM Luck luck WHERE luck.game.id = :id AND luck.id = :luckId")
	Optional<Luck> findLuckByGameId(@Param("id") Integer id, @Param("luckId") Integer luckId);
	
	@Query("SELECT luck FROM Luck luck WHERE luck.game.id = 0")
	Set<Luck> findBlankLucks();
}
