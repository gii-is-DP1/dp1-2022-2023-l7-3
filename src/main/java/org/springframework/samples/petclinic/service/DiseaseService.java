package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disease;
import org.springframework.samples.petclinic.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
	
	@Autowired
	DiseaseRepository diseaseRepo;
	
	public Collection<Disease> findAll(){
		return diseaseRepo.findAll();
	}
	

	public Optional<Disease> findById(int id) {
		return diseaseRepo.findById(id);
	}


	public void delete(Disease disease) {
		diseaseRepo.deleteById(disease.getId());
		
	}


	public void save(@Valid Disease disease) {		
		diseaseRepo.save(disease);
		
	}
}
