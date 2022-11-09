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
	private CompanyRepository companyRepository;
	private StationRepository stationRepository;
	
	

	@Autowired
	public PropertyService(PropertyRepository propertyRepository, PlayerRepository playerRepository,
			StreetRepository streetRepository, CompanyRepository companyRepository, StationRepository stationRepository) {
		this.propertyRepository = propertyRepository;
		this.playerRepository = playerRepository;
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
		
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
		Integer n =0;
		Optional<Property> property = propertyRepository.findById(idProperty);
		Player player = playerRepository.findPlayerById(idPlayer);
		Player owner = property.get().getOwner();
		if(streetRepository.findIsStreetById(idProperty)) {
			n = payStreet(idProperty, idPOwner);	
		}else if (stationRepository.findIsStationById(idProperty)) {
			n = payStation(idProperty, idPOwner);
		}
		
		player.setMoney(player.getMoney() - n);
		owner.setMoney(owner.getMoney() + n);
			
		
	}

	private Integer payStation(Integer idProperty, Integer idPOwner) {
		Station station = stationRepository.findStationById(idProperty);
		Integer n = (int) stationRepository.findByOwner(idPOwner).stream().count();
		return station.getRentalPrice()*n;
	}

	private Integer payStreet(Integer idProperty, Integer idPOwner) {
		Street street = streetRepository.findStreetById(idProperty);
		Boolean b = propertyRepository.findByColor(street.getColor().toString()).stream().allMatch(x->x.getOwner().getId()==idPOwner);
		if(b) {
			if(street.getHaveHotel()) {
				return street.getRentalHotel();
			}else {
				switch (street.getHouseNum()) {
					case 1: return street.getRental1House();
					case 2: return street.getRental2House();
					case 3: return street.getRental3House();
					case 4: return street.getRental4House();
					default: return street.getRentalPrice()*2;
				}
			}			
		} else {
			return street.getRentalPrice();
		}		
	}
	
	
	
}
