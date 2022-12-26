/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.monopoly.player;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface PlayerRepository extends CrudRepository<Player, Integer> {
	Collection<Player> findAll();

	
	Player findPlayerById(Integer id);

	@Query("SELECT p FROM Player p WHERE p.id = :idparam AND p.game.id = :idgame")
	Player findPlayerById(@Param("idparam") Integer id , @Param("idgame") Integer id2);
	
	@Query("SELECT player FROM Player player WHERE player.user =:id")
	Optional<Player> findPlayerByUser(@Param("id") Integer id);
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE player SET user_id = 0 WHERE user_id = :userId")
	void updatePlayerRelation(@Param("userId") Integer id);
	
	@Query(nativeQuery = true, 
		   value = "SELECT name FROM streets WHERE game = :gameId AND owner = :playerId UNION"
		   		+ " SELECT name FROM companies WHERE game = :gameId AND owner = :playerId UNION"
		   		+ " SELECT name FROM stations WHERE game = :gameId AND owner = :playerId")
	List<String> findAllPropertyNamesByPlayer(@Param("gameId") Integer gameId, @Param("playerId") Integer playerId);
	 
}
