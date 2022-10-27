package org.springframework.monopoly.property;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class StreetService {
	
	private StreetRepository streetRepository;

	@Autowired
	public StreetService(StreetRepository streetRepository) {
		this.streetRepository = streetRepository;
	}

	@Transactional
	public void saveStreet(Street street) throws DataAccessException {
		streetRepository.save(street);
	}
	
	public Optional<Street> findStreet(Integer id) {
		return streetRepository.findById(id);
	}

}
