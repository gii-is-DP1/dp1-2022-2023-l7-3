package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StreetRepository extends  CrudRepository<Street, Integer>{
	
	boolean findIsStreetById(Integer id);

	Street findStreetById(Integer id);

	@Query("SELECT s.color FROM Street s")
	List<String> findAllColor();

	@Query("SELECT DISTINCT s.name FROM Street s WHERE s.color = :color")
	List<String> findByColor(@Param("color")String color);
	
}