package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface StationRepository extends  CrudRepository<Station, Integer>{
	
	
	@Query("SELECT st FROM Station st WHERE st.id = :idparam AND st.game.id = :idgame")
	Boolean findIsStationById(@Param("idparam")Integer id,@Param("idgame") Integer id2);
	
	@Query("SELECT st FROM Station st WHERE st.id = :idparam AND st.game.id = :idgame")
	Station findStationById(@Param("idparam")Integer id,@Param("idgame") Integer id2);
	
	@Query("SELECT st FROM Station st WHERE st.owner = :idOwner")
	List<Station> findByOwner(@Param("idOwner")Integer id);
}