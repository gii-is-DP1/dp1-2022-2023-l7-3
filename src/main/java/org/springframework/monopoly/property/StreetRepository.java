package org.springframework.monopoly.property;

import org.springframework.data.repository.CrudRepository;

public interface StreetRepository extends  CrudRepository<Street, Integer>{
	
	boolean findIsStreetById(Integer id);
	Street findStreetById(Integer id);
	
}