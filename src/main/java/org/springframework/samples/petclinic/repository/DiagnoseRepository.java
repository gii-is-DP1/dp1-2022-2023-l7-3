package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Diagnose;

public interface DiagnoseRepository extends  CrudRepository<Diagnose, Integer>{
	@Query("SELECT d FROM Diagnose d WHERE d.visit.pet.id = :petId")
	Collection<Diagnose> findByPetId(@Param("petId")int petId);
	Collection<Diagnose> findAll();
}
