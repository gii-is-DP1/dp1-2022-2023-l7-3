package org.springframework.monopoly.property;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;




public interface PropertyRepository extends  CrudRepository<Property, Integer>{
	
	Optional<Property>  findById(Integer id);
	
	@Query("SELECT s FROM Street s WHERE s.color = :color")
	List<Street> findByColor(@Param("color")String color);
	
}
