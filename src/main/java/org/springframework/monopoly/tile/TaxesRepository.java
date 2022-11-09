package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TaxesRepository extends CrudRepository<Taxes, Integer>{
	List<Taxes> findAll();
	
	Optional<Taxes> findById();
}
