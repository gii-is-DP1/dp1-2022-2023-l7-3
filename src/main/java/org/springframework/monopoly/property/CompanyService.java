package org.springframework.monopoly.property;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class CompanyService {
	
	private CompanyRepository companyRepository;

	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Transactional
	public void saveCompany(Company company) throws DataAccessException {
		companyRepository.save(company);
	}
	
	public Optional<Company> findStreet(Integer id) {
		return companyRepository.findById(id);
	}


}
