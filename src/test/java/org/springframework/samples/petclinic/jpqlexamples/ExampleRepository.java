package org.springframework.samples.petclinic.jpqlexamples;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;

public interface ExampleRepository extends Repository<Owner, Integer> {

    void save(Owner o) throws DataAccessException;

    @Query("SELECT o FROM Owner o")
    List<Owner> findAllOwners() throws DataAccessException;

    @Query("SELECT p.owner FROM Pet p")
    List<Owner> findAllOwnersOfPets() throws DataAccessException;

    @Query("SELECT DISTINCT p.owner FROM Pet p")
    List<Owner> findAllUniqueOwnersOfPets() throws DataAccessException;

    @Query("SELECT o.pets FROM Owner o")
    List<List<Pet>> findAllPetsOfOwnerImplicit() throws DataAccessException;

    @Query("SELECT p FROM Owner o INNER JOIN o.pets p")
    List<Pet> findAllPetsOfOwner() throws DataAccessException;

    @Query("SELECT o FROM Owner o INNER JOIN o.pets p")
    List<Owner> findAllOwnersOfPetsInner() throws DataAccessException;

    @Query("SELECT o FROM Owner o LEFT JOIN o.pets p")
    List<Owner> findAllOwnersPerPet() throws DataAccessException;

    @Query("SELECT DISTINCT o FROM Owner o INNER JOIN o.pets p INNER JOIN p.visits v")
    List<Owner> findAllOwnersWithPetsWithVisits() throws DataAccessException;

    @Query("SELECT p FROM Pet p WHERE p.birthDate BETWEEN :from AND :to")
    List<Pet> findPetInDates(LocalDate from, LocalDate to) throws DataAccessException;

    @Query("SELECT o FROM Owner o WHERE o.lastName LIKE :lastName%")
    List<Owner> findOwnerByLastName(String lastName);

    @Query("SELECT p FROM Pet p JOIN p.type type WHERE type.name IN :types")
    List<Pet> findPetInTypes(List<String> types);

    @Query("SELECT p FROM Pet p WHERE p.visits IS NOT EMPTY")
    List<Pet> findPetsWithVisits();

    @Query("SELECT o FROM Owner o WHERE size(o.pets) > :threshold")
    List<Owner> findOwnerWithMorePetsThan(int threshold);

    @Query("SELECT p FROM Pet p, Owner o WHERE o=:o AND p MEMBER OF o.pets")
    List<Pet> findPetsOfOwner(Owner o);

	public Owner findById(int id);

    @Query("SELECT p.type.name, count(p) FROM Pet p GROUP BY p.type.name")
    public List<Object []> countPetTypes();  
    
    @Query("SELECT p FROM Pet p WHERE size(p.visits) > ALL (SELECT (count(v)*1.0/count(p2)) FROM Pet p2 LEFT JOIN p2.visits v GROUP BY p2.type.name HAVING p2.type = p.type)")
    public List<Pet> findPetsWithVisitsOverTheAverageOfTheirPetType();


    @Query("SELECT o.firstName, o.lastName FROM Owner o")
    List<Object[]> findAllOwnersOnlyNames();

    @Query("SELECT new org.springframework.samples.petclinic.jpqlexamples.OwnerView(owner.firstName, owner.lastName) FROM Owner owner")
    List<OwnerView> findAllOwnersAsOwnerView();

    @Query("SELECT p.name AS name, p.owner as owner FROM Pet p")
    List<PetView> findAllPetsAsPetView();


    @Query("SELECT p FROM Pet p")
    List<Pet> findAllPets();
}
