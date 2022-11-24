package org.springframework.monopoly.tile;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommunityBoxRepository extends CrudRepository<CommunityBox, Integer> {
	
	@Query("SELECT cB FROM CommunityBox cB WHERE cB.game.id = :id")
	List<CommunityBox> findAllCommunityBoxByGameId(@Param("id") Integer id);
	
	@Query("SELECT cB FROM CommunityBox cB WHERE cB.game.id = :id AND cB.id = :communityBoxId")
	CommunityBox findCBByGameId(@Param("id") Integer id, @Param("communityBoxId") Integer luckId);
}
