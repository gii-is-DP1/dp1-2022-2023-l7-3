package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

	
	@Autowired
	VisitRepository visitRepository;
	
	public Optional<Visit> findById(int visitId){
		return visitRepository.findById(visitId);
	}
	
	public List<Visit> findAll(){
		return visitRepository.findAll();
	}
}
