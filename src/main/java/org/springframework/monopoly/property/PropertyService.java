package org.springframework.monopoly.property;

import org.hibernate.boot.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public Property findProperty(Integer id,Integer idgame) throws MappingException{
		return propertyRepository.findPropertyById(id, idgame);
	}

	public Trio<Boolean, Boolean, Integer> hasOwner (Turn turn, Integer tileId, Integer tirada) {
		Property property = propertyRepository.findPropertyById(tileId,turn.getGame().getId());
		if(property != null) {
			if(property.getOwner() == null) {
				return buyPropertyById(property.getId(), turn.getPlayer());
			} else {
				return payPropertyById(property.getId(),property.getGame().getId() ,turn.getPlayer(), tirada);
			}
		} else {
			return null;
		}
	}
	
	public Trio<Boolean, Boolean, Integer> buyPropertyById(Integer idProperty, Player player) {
		Property property = propertyRepository.findPropertyById(idProperty, player.getGame().getId());
		if(player.getMoney()>= property.getPrice()) {
			player.setMoney(player.getMoney() - property.getPrice());
			property.setOwner(player);
			return Trio.of(false, true, property.getPrice());
		}
		
		return Trio.of(false, false, null); // temporal
		// si no es mayor se manda a subasta
	}
	
	public Trio<Boolean, Boolean, Integer> payPropertyById(Integer idProperty, Integer idGame,Player player, Integer tirada) {
		Integer n =0;
		Property property = propertyRepository.findPropertyById(idProperty,idGame);
		Integer idPOwner = property.getOwner().getId();
		if(streetRepository.findIsStreetById(idProperty,idGame)) {
			n = payStreet(idProperty, idGame,idPOwner);	
		}else if (stationRepository.findIsStationById(idProperty,idGame)) {
			n = payStation(idProperty,idGame, idPOwner);
		} else if (companyRepository.findIsCompanyById(idProperty,idGame)) {
			n = payCompany(idProperty,idGame, idPOwner, tirada);
		}
		
		if (player.getMoney() >= n) {
			player.setMoney(player.getMoney() - n);
			property.getOwner().setMoney(property.getOwner().getMoney() + n);
		}
		
		return Trio.of(true, null, n); // Temporal
		// si no, se hipoteca		
	}

	private Integer payStreet(Integer idProperty, Integer idGame, Integer idPOwner) {
		Street street = streetRepository.findStreetById(idProperty,idGame);
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

	private Integer payStation(Integer idProperty,Integer idGame, Integer idPOwner) {
		Station station = stationRepository.findStationById(idProperty,idGame);
		Integer n = (int) stationRepository.findByOwner(idPOwner).stream().count();
		return station.getRentalPrice()*n;
	}

	private Integer payCompany(Integer idProperty,Integer idGame, Integer idPOwner, Integer tirada) {
		Company company = companyRepository.findCompanyById(idProperty,idGame);
		Integer n = 4;
		if (companyRepository.findByOwner(idPOwner,idGame).stream().count() == 2.) n = 10;
		return company.getRentalPrice() * n * tirada; // (Hecho) falta multiplicarlo por la tirada específica para las compañias
	}
	
	

}