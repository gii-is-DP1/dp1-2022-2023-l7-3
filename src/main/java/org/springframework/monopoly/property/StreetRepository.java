package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StreetRepository extends  CrudRepository<Street, Integer>{

	@Query("SELECT s FROM Street s WHERE s.id = :idparam AND s.game.id = :idgame")
	Street findStreetById(@Param("idparam") Integer id , @Param("idgame") Integer id2);

	@Query("SELECT s.color FROM Street s")
	List<String> findAllColor();

	@Query("SELECT s FROM Street s WHERE s.color = :color AND s.game.id = :idgame")
	List<Street> findStreetByColor(@Param("color")String color,@Param("idgame") Integer idgame);
	
	@Query("SELECT DISTINCT s.name FROM Street s WHERE s.color = :color")
	List<String> findByColor(@Param("color")String color);

	//añadir query que coja la lista de los colores del jugador
	
	// ↓↓↓↓ Query que coge la lista de los colores del jugador:
	// ↓↓↓↓ "Una query de su padre y de su madre con la que he triunfao" - Victor (probably)
	@Query(nativeQuery = true, 
			value="SELECT DISTINCT color "
				+ "FROM (SELECT color, owner "
				+ 		"FROM streets "
				+ 		"WHERE game_id = :gameId) "
				+ "WHERE owner = :playerId AND color IN "
				+ 		"(SELECT color"
				+ 		" FROM (SELECT color,count(color) AS count "
				+ 				"FROM (SELECT color, owner "
				+ 					  "FROM streets "
				+ 					  "WHERE game_id = 2 "
				+ 					  "GROUP BY color, owner) "
				+ 				"GROUP BY color)"
				+ 		" WHERE count = 1)")
	List<Color> findPlayerColors(@Param("gameId") Integer gameId, @Param("playerId") Integer playerId);
	
}