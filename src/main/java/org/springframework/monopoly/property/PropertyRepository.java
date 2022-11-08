package org.springframework.monopoly.property;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;




public interface PropertyRepository extends  CrudRepository<Property, Integer>{
	
	Optional<Property>  findById(Integer id);
	
}
