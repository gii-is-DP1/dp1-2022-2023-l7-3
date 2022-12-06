package org.springframework.monopoly.property;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;

	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Transactional
	public Company saveCompany(Company company) throws DataAccessException {
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company findCompany(Integer id, Integer idgame) {
		return companyRepository.findCompanyById(id,idgame);
	}
	
	@Transactional
	public Set<Company> getBlankCompanies() {
		return companyRepository.getBlankCompanies();
	}
	
}
