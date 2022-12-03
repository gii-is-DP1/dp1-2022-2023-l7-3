package org.springframework.monopoly.property;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StationService {

	private StationRepository stationRepository;

	@Autowired
	public StationService(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	@Transactional
	public void saveStation(Station station) throws DataAccessException {
		stationRepository.save(station);
	}
	
	@Transactional
	public Station findStation(Integer id, Integer idgame) {
		return stationRepository.findStationById(id,idgame);
	}
	
	@Transactional(readOnly = true)
	public Set<Station> getBlankStations() {
		return stationRepository.getBlankStations();
	}
}
