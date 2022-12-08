package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommunityBoxRepository extends CrudRepository<CommunityBox, Integer> {
	
	@Query("SELECT cB FROM CommunityBox cB WHERE cB.game.id = :gameId")
	List<CommunityBox> findAllCommunityBoxByGameId(@Param("gameId") Integer gameId);
	
	@Query("SELECT cB FROM CommunityBox cB WHERE cB.game.id = :gameId AND cB.id = :communityBoxId")
	Optional<CommunityBox> findCBByGameId(@Param("gameId") Integer gameId, @Param("communityBoxId") Integer communityBoxId);

	@Query("SELECT cB FROM CommunityBox cB WHERE cB.game.id = 0")
	Set<CommunityBox> findBlankCommunityBoxes();
}
