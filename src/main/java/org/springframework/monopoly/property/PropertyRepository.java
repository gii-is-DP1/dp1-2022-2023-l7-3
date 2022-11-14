package org.springframework.monopoly.property;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;




public interface PropertyRepository extends  CrudRepository<Property, Integer>{
	
	@Query(nativeQuery= true, value = "SELECT p FROM(SELECT p FROM streets UNION SELECT p FROM stations UNION SELECT p FROM companies) WHERE p.id = :idparam AND p.game.id = :idgame ")
	Property  findPropertyById(@Param("idparam")Integer id,@Param("idgame") Integer id2);
	
	@Query("SELECT s FROM Street s WHERE s.color = :color")
	List<Street> findByColor(@Param("color")String color);
	
}
