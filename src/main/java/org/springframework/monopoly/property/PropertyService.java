package org.springframework.monopoly.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public PropertyService(PropertyRepository propertyRepository, StreetRepository streetRepository, CompanyRepository companyRepository, 
			StationRepository stationRepository) {
		this.propertyRepository = propertyRepository;
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
	}
	

	@Transactional
	public void saveProperty(Property property) throws DataAccessException {
		propertyRepository.save(property);
		
	}

	public Object getProperty(Integer idProperty, Integer idGame) {
		if (streetRepository.findStreetById(idProperty, idGame) !=null ) {
			return streetRepository.findStreetById(idProperty, idGame);
		} else if(companyRepository.findCompanyById(idProperty, idGame) != null) {
			return companyRepository.findCompanyById(idProperty, idGame);
		} else if (stationRepository.findStationById(idProperty, idGame) != null) {
			return stationRepository.findStationById(idProperty, idGame);
		}else {
			return null;//Tenemos que poner una excepcion en lugar de un null
		}
		
	}


	public Trio<Boolean, Boolean, Integer> hasOwner (Turn turn, Integer tileId, Integer tirada) {
		Property property = (Property) getProperty(tileId, turn.getGame().getId());
		if(property !=null) {
			if( property.getOwner() == null) {
				return buyPropertyById(property, turn);
			} else {
				return payPropertyById(property, turn, tirada);
			}
		} else {
			return null;
		}
	}
	
	public Trio<Boolean, Boolean, Integer> buyPropertyById(Property property, Turn turn) {	
		if(turn.getPlayer().getMoney()>= property.getPrice()) {
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - property.getPrice());
			property.setOwner(turn.getPlayer());
			return Trio.of(false, true, property.getPrice());
		} // si no es mayor se manda a subasta
		return Trio.of(false, false, null); // temporal
	}
	
	public Trio<Boolean, Boolean, Integer> payPropertyById(Property property, Turn turn, Integer tirada) {
		Integer n =0;
		if(streetRepository.findStreetById(property.getId(),property.getGame().getId()) != null) {
			n = payStreet(property);	
		}else if (stationRepository.findStationById(property.getId(),property.getGame().getId()) != null) {
			n = payStation(property);
		} else if (companyRepository.findCompanyById(property.getId(),property.getGame().getId()) != null) {
			n = payCompany(property, tirada);
		}
		
		if (turn.getPlayer().getMoney() >= n) {
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - n);
			property.getOwner().setMoney(property.getOwner().getMoney() + n);
		}
		
		return Trio.of(true, null, n); // Temporal
		// si no, se hipoteca		
	}

	private Integer payStreet(Property property) {
		Street street = (Street)property;
		Boolean b = streetRepository.findStreetByColor(street.getColor().toString(), street.getGame().getId()).stream().allMatch(x -> x.getOwner() == street.getOwner());
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

	private Integer payStation(Property property) {
		Station station = (Station) property;
		Integer n = (int) stationRepository.findByOwner(station.getOwner().getId(),station.getGame().getId()).stream().count();
		return station.getRentalPrice()*n;
	}

	private Integer payCompany(Property property, Integer tirada) {
		Company company = (Company) property;
		Integer n = 4;
		if (companyRepository.findByOwner(company.getOwner().getId(),company.getGame().getId()).stream().count() == 2.) n = 10;
		return company.getRentalPrice() * n * tirada; // (Hecho) falta multiplicarlo por la tirada específica para las compañias
	}
	
	

}


