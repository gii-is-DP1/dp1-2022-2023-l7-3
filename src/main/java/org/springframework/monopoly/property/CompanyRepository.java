package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends  CrudRepository<Company, Integer>{
	
	boolean findIsCompanyById(Integer id);
	Company findCompanyById(Integer id);

	@Query("SELECT c FROM Company c WHERE c.owner = :idOwner")
	List<Company> findByOwner(@Param("idOwner")Integer id);	
}
