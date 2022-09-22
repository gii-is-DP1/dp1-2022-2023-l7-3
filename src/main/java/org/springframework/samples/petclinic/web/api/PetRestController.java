package org.springframework.samples.petclinic.web.api;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.BadRequestException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.service.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
public class PetRestController {
	
	@Autowired
	private PetService petService;
	
	@GetMapping
	public Collection<Pet> findAll() {
		return petService.findAllPets();
	}
	
	@GetMapping("/{id}")
	public Pet findbyId(@PathVariable("id") int id) {
		return petService.findPetById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pet create(@RequestBody Pet resource) {
		if(resource == null) {
			throw new BadRequestException();
		} else {
			try {
				petService.savePet(resource);
			} catch (DuplicatedPetNameException ex) {
				throw new BadRequestException();
			}
		}
		
		return resource;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Pet resource, @PathVariable("id") int id) {
		if(resource == null) {
			throw new BadRequestException();
		} else if(petService.findPetById(id) == null) {
			throw new ResourceNotFoundException();
		} else {
			try {
				petService.savePet(resource);
			} catch (DuplicatedPetNameException ex) {
				throw new BadRequestException();
			}
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		petService.deletePet(id);
	}

}
