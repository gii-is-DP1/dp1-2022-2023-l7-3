package org.springframework.monopoly.property;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.util.Trio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

	private PropertyRepository propertyRepository;
	private StreetRepository streetRepository;
	private CompanyRepository companyRepository;
	private StationRepository stationRepository;
	
	@Autowired
	public PropertyService(PropertyRepository propertyRepository, StreetRepository streetRepository, CompanyRepository companyRepository, StationRepository stationRepository) {
		this.propertyRepository = propertyRepository;
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
	}

	@Transactional
	public void saveProperty(Property property) throws DataAccessException {
		propertyRepository.save(property);
	}
	
	@Transactional
	public Optional<Property> findProperty(Integer id) {
		return propertyRepository.findById(id);
	}

	@Transactional
	public List<Property> getAllGameProperties() {
		
	}
	
	public Trio<Boolean, Boolean, Integer> hasOwner (Turn turn, Integer tileId, Integer tirada) {
		Optional<Property> property = propertyRepository.findById(tileId);
		if(property.isPresent()) {
			if(property.get().getOwner() == null) {
				return buyPropertyById(property.get().getId(), turn.getPlayer());
			} else {
				return payPropertyById(property.get().getId(), turn.getPlayer(), tirada);
			}
		} else {
			return null;
		}
	}
	
	public Trio<Boolean, Boolean, Integer> buyPropertyById(Integer idProperty, Player player) {
		Optional<Property> property = propertyRepository.findById(idProperty);
		if(player.getMoney()>= property.get().getPrice()) {
			player.setMoney(player.getMoney() - property.get().getPrice());
			property.get().setOwner(player);
			return Trio.of(false, true, property.get().getPrice());
		}
		
		return Trio.of(false, false, null); // temporal
		// si no es mayor se manda a subasta
	}
	
	public Trio<Boolean, Boolean, Integer> payPropertyById(Integer idProperty, Player player, Integer tirada) {
		Integer n =0;
		Optional<Property> property = propertyRepository.findById(idProperty);
		Integer idPOwner = property.get().getOwner().getId();
		if(streetRepository.findIsStreetById(idProperty)) {
			n = payStreet(idProperty, idPOwner);	
		}else if (stationRepository.findIsStationById(idProperty)) {
			n = payStation(idProperty, idPOwner);
		} else if (companyRepository.findIsCompanyById(idProperty)) {
			n = payCompany(idProperty, idPOwner, tirada);
		}
		
		if (player.getMoney() >= n) {
			player.setMoney(player.getMoney() - n);
			property.get().getOwner().setMoney(property.get().getOwner().getMoney() + n);
		}
		
		return Trio.of(true, null, n); // Temporal
		// si no, se hipoteca		
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

	private Integer payStation(Integer idProperty, Integer idPOwner) {
		Station station = stationRepository.findStationById(idProperty);
		Integer n = (int) stationRepository.findByOwner(idPOwner).stream().count();
		return station.getRentalPrice()*n;
	}

	private Integer payCompany(Integer idProperty, Integer idPOwner, Integer tirada) {
		Company company = companyRepository.findCompanyById(idProperty);
		Integer n = 4;
		if (companyRepository.findByOwner(idPOwner).stream().count() == 2.) n = 10;
		return company.getRentalPrice() * n * tirada; // (Hecho) falta multiplicarlo por la tirada específica para las compañias
	}

}