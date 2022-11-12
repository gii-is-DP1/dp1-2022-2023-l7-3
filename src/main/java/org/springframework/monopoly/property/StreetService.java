package org.springframework.monopoly.property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	@Transactional
	public Optional<Street> findStreet(Integer id) {
		return streetRepository.findById(id);
	}
	
	public List<Color> getStreetsColors(List<Property> properties) {
		Set<Color> colors = new HashSet<Color>();
		
		for(Property p:properties) {
			if(p instanceof Street) {
				Street c = (Street) p;
				colors.add(c.getColor());
			}
		}
		
		return new ArrayList<Color>(colors);
	}

}
