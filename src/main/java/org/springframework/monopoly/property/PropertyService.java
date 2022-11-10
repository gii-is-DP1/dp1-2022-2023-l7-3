package org.springframework.monopoly.property;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Turn;
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
	
	public Optional<Property> findProperty(Integer id) {
		return propertyRepository.findById(id);
	}

	public void hasOwner (Turn turn) {
		Optional<Property> property = propertyRepository.findById(turn.getFinal_tile());
		if(property != null) {
			if(property.get().getOwner() == null) {
				buyPropertyById(property.get().getId(), turn.getPlayer());
			} else {
				payPropertyById(property.get().getId(), turn.getPlayer());
			}
		}
	}
	
	public void buyPropertyById(Integer idProperty, Player player) {
		Optional<Property> property = propertyRepository.findById(idProperty);
		if(player.getMoney()>= property.get().getPrice()) {
			property.get().setOwner(player);
		}

		// si no es mayor se manda a subasta
	}
	
	public void payPropertyById(Integer idProperty, Player player) {
		Integer n =0;
		Optional<Property> property = propertyRepository.findById(idProperty);
		Integer idPOwner = property.get().getOwner().getId();
		if(streetRepository.findIsStreetById(idProperty)) {
			n = payStreet(idProperty, idPOwner);	
		}else if (stationRepository.findIsStationById(idProperty)) {
			n = payStation(idProperty, idPOwner);
		} else if (companyRepository.findIsCompanyById(idProperty)) {
			n = payCompany(idProperty, idPOwner);
		}
		
		if (player.getMoney() >= n) {
			player.setMoney(player.getMoney() - n);
			property.get().getOwner().setMoney(property.get().getOwner().getMoney() + n);
		}
		
		// si no se hipoteca		
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

	private Integer payCompany(Integer idProperty, Integer idPOwner) {
		Company company = companyRepository.findCompanyById(idProperty);
		Integer n = 4;
		if (companyRepository.findByOwner(idPOwner).stream().count() == 2.) n = 10;
		return company.getRentalPrice() * n ; // falta multiplicarlo por la tirada específica para las compañias
	}

}