package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends  CrudRepository<Company, Integer>{
	
	@Query("SELECT c FROM Company c WHERE c.id = :idparam AND c.game.id = :idgame")
	Company findCompanyById(@Param("idparam")Integer id,@Param("idgame") Integer id2);

	@Query("SELECT c FROM Company c WHERE c.owner.id = :idOwner AND c.game.id = :idgame")
	List<Company> findByOwner(@Param("idOwner")Integer id,@Param("idgame") Integer id2);	
}
