package org.springframework.monopoly.property;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends  CrudRepository<Company, Integer>{
	
	boolean findIsCompanyById(Integer id);
	Company findCompanyById(Integer id);
	
}
