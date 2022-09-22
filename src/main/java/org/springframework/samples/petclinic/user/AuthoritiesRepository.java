package org.springframework.samples.petclinic.user;

import org.springframework.data.repository.CrudRepository;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
