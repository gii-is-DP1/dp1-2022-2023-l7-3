package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Disease;


public interface DiseaseRepository extends  CrudRepository<Disease, Integer>{
	Collection<Disease> findAll();
}
