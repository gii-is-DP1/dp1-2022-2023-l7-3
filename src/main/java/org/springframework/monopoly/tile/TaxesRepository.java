package org.springframework.monopoly.tile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaxesRepository extends CrudRepository<Taxes, Integer>{
	
	List<Taxes> findAll();
}
