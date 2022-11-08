package org.springframework.monopoly.property;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

	private PropertyRepository propertyRepository;

	@Autowired
	public PropertyService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	@Transactional
	public void saveProperty(Property property) throws DataAccessException {
		propertyRepository.save(property);
	}
	
	public Optional<Property> findProperty(Integer id) {
		return propertyRepository.findById(id);
	}
	
}
