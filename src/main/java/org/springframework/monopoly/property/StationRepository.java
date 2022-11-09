package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface StationRepository extends  CrudRepository<Station, Integer>{
	boolean findIsStationById(Integer id);
	Station findStationById(Integer id);
	
	@Query("SELECT s FROM Station s WHERE s.owner = :idOwner")
	List<Station> findByOwner(@Param("idOwner")Integer id);
}