
/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.jpqlexamples;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.pet.Pet;


@DataJpaTest(
excludeFilters = @ComponentScan.Filter(OwnerRepository.class))
class ExampleServiceTests {        
	
    private static final Logger log = LoggerFactory.getLogger(ExampleServiceTests.class);
    
	@Autowired
	protected ExampleRepository repository;

	@Test
	void test() {

		repository.save(ownerWithoutPets());

		// Selection and Projection
		log.info("Running findAllOwners()");
		log.info(toString(this.repository.findAllOwners()));
		log.info("Running findAllOwnersOfPets()");
		log.info(toString(this.repository.findAllOwnersOfPets()));
		log.info("Running findAllUniqueOwnersOfPets()");
		log.info(toString(this.repository.findAllUniqueOwnersOfPets()));
		log.info("Running findAllPetsOfOwnerImplicit()");
		log.info(toString(this.repository.findAllPetsOfOwnerImplicit()));
		log.info("Running findAllPetsOfOwner()");
		log.info(toString(this.repository.findAllPetsOfOwner()));
		log.info("Running findAllOwnersOfPetsInner()");
		log.info(toString(this.repository.findAllOwnersOfPetsInner()));
		log.info("Running findAllOwnersPerPet()");
		log.info(toString(this.repository.findAllOwnersPerPet()));		
		log.info("Running findAllOwnersWithPetsWithVisits()");
		log.info(toString(this.repository.findAllOwnersWithPetsWithVisits()));

		log.info("Running findAllOwnersOnlyNames()");
		log.info(toString(this.repository.findAllOwnersOnlyNames()));
		log.info("Running findAllOwnersAsOwnerView()");
		log.info(toString(this.repository.findAllOwnersAsOwnerView()));		
		log.info("Running findAllPetsAsPetView()");
		log.info(toString(this.repository.findAllPetsAsPetView()));		

		// Restrictions

		log.info("Running findPetInDates()");
		log.info(toString(this.repository.findPetInDates(LocalDate.of(2011, 1, 1), LocalDate.of(2012,6,30))));		
		log.info("Running findOwnerByLastName()");
		log.info(toString(this.repository.findOwnerByLastName("Scott")));		
		log.info("Running findPetInTypes()");
		log.info(toString(this.repository.findPetInTypes(List.of("lizard", "bird"))));		
		log.info("Running findPetsWithVisits()");
		log.info(toString(this.repository.findPetsWithVisits()));		
		log.info("Running findOwnerWithMorePetsThan()");
		log.info(toString(this.repository.findOwnerWithMorePetsThan(1)));	
		Owner o6 = this.repository.findById(6)	;
		log.info("Running findPetsOfOwner()");
		log.info(toString(this.repository.findPetsOfOwner(o6)));	
		
		log.info("Running countPetTypes()");
		log.info(toString(this.repository.countPetTypes()));	
		log.info("Running findPetsWithVisitsOverTheAverageOfTheirPetType()");
		log.info(toString(this.repository.findPetsWithVisitsOverTheAverageOfTheirPetType()));				
	}

	@Test
	public void fetchExamples() {
		List<Owner> owners = this.repository.findAllOwners();
		log.info(toString(owners));
		for (Owner o: owners) {
			log.info(o.getUser().getPassword());
			List<Pet> petsOwner = o.getPets();
			log.info(toString(petsOwner));
			for (Pet p : petsOwner) {
				log.info(toString(p.getVisits()));
			}
		}
	}

	private Owner ownerWithoutPets() {
		Owner ownerWithoutPets = new Owner();
		ownerWithoutPets.setFirstName("Michael");
		ownerWithoutPets.setLastName("Scott");
		ownerWithoutPets.setCity("Scranton");
		ownerWithoutPets.setTelephone("999666999");
		ownerWithoutPets.setAddress("Dunder Mifflin Street");

		return ownerWithoutPets;
	}

	private <T> String toString(List<T> objects) {
		StringBuilder b = new StringBuilder("[\n");		
		for (T o: objects) {
			if (o instanceof Object[]) {
				Object[] oArray = (Object []) o;
				for (Object elem: oArray) {
					b.append(elem.toString());
					b.append(", ");
				}
			} else {
				b.append(o.toString());
			}
			b.append("\n");	
		}
		b.append("]");
		b.append(" Number of elements: ");
		b.append(objects.size());
		return b.toString();
	}




}
