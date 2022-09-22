package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import java.util.List;


public interface UserRepository extends  CrudRepository<User, String>{
	
}
