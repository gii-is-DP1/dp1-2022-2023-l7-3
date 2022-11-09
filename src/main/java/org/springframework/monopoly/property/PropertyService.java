package org.springframework.monopoly.property;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.expr.Instanceof;

@Service
public class PropertyService {

	private PropertyRepository propertyRepository;
	private PlayerRepository playerRepository;
	private StreetRepository streetRepository;
	
	

	@Autowired
	public PropertyService(PropertyRepository propertyRepository, PlayerRepository playerRepository,StreetRepository streetRepository) {
		this.propertyRepository = propertyRepository;
		this.playerRepository = playerRepository;
		this.streetRepository = streetRepository;
	}

	@Transactional
	public void saveProperty(Property property) throws DataAccessException {
		propertyRepository.save(property);
	}
	
	public Optional<Property> findProperty(Integer id) {
		return propertyRepository.findById(id);
	}
	
	public void buyPropertyById(Integer idProperty, Integer idPlayer) {
		Optional<Property> property = propertyRepository.findById(idProperty);
		Player player = playerRepository.findPlayerById(idPlayer);
		if(player.getMoney()>= property.get().getPrice()) {
			property.get().setOwner(player);
		}
	}
	
	public void payPropertyById(Integer idProperty, Integer idPlayer, Integer idPOwner) {
		Optional<Property> property = propertyRepository.findById(idProperty);
		Player player = playerRepository.findPlayerById(idPlayer);
		Player owner = property.get().getOwner();
		if(streetRepository.findIsStreetById(idProperty)) {
			payStreetById(idProperty,idPlayer,idPOwner);
			
		}
		
		player.setMoney(player.getMoney()-property.get().getRentalPrice());
		owner.setMoney(owner.getMoney()+property.get().getRentalPrice());
			
		
	}

	private void payStreetById(Integer idProperty, Integer idPlayer, Integer idPOwner) {
		Street street = streetRepository.findStreetById(idProperty);
		List<Street> streets = propertyRepository.findByColor(street.getColor().toString());
		Boolean b = streets.stream().allMatch(x->x.getOwner().getId()==idPOwner);
		Boolean b2 = b && streets.stream().anyMatch(x->x.getHouseNum()>0);
		if(b) {
			
		}
		
	}
	
	
	
}
