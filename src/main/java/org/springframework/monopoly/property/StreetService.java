package org.springframework.monopoly.property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
	public Street findStreet(Integer id, Integer idgame) {
		return streetRepository.findStreetById(id,idgame);
	}
	
	public List<Color> getStreetsColors(Set<Street> streets) {
		Set<Color> colors = new HashSet<Color>();
		
		for(Street s:streets) {
			colors.add(s.getColor());
		}
		
		return new ArrayList<Color>(colors);
	}
	
	// New
	@Transactional
	public List<Color> findPlayerColors(Player player) {
		return streetRepository.findPlayerColors(player.getGame().getId(), player.getId());
	}

}
