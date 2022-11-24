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
	
}