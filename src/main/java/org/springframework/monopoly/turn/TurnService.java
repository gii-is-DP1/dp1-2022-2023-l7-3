package org.springframework.monopoly.turn;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.util.Trio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnService {

	private static final List<Integer> streets = List.of(1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39);
	private static final List<Integer> companies = List.of(12,28);
	private static final List<Integer> stations = List.of(5,15,25,35);
	private static Random random = new Random();
	
	private TurnRepository turnRepository;
	private static PropertyService propertyService;

	@Autowired
	public TurnService(TurnRepository turnRepository, PropertyService propertyService) {
		this.turnRepository = turnRepository;
		TurnService.propertyService = propertyService;
	}

	@Transactional
	public void saveTurn(Turn turn) throws DataAccessException {
		turnRepository.save(turn);
	}
	
	@Transactional
	public Optional<Turn> findTurn(Integer id) {
		return turnRepository.findById(id);
	}
	
	@Transactional
	public Turn findLastTurn(Integer gameId) {
		return turnRepository.findLastTurn(gameId);
	}
	
	public Turn calculateTurn(Turn turn) {
		Integer roll1 = getRoll();
		Integer tile1 = turn.getInitial_tile() + roll1;
		Trio<String, Action, Integer> action1 = getTileAction(turn, tile1, roll1);

		turn.getActions().add(action1.getSecond());
		turn.setQuantity(action1.getThird());
		
		return turn;
	}
	
	private Integer getRoll() {
		return random.ints(1, 13).findFirst().getAsInt();
	}
	
	// TODO terminar de calcular cosas
	public static Trio<String, Action, Integer> getTileAction(Turn turn, Integer id, Integer tirada) {
		Trio<String, Action, Integer> res = null;
		if(streets.contains(id)) {
			// Street
			Trio<Boolean, Boolean, Integer> hasOwner = propertyService.hasOwner(turn, id, tirada);
			if(hasOwner.getFirst()) {
				// No tiene owner
				res = Trio.of("OwnedStreet", Action.PAY, hasOwner.getThird());
			} else {
				// tiene owner
				res = hasOwner.getSecond() ? Trio.of("Street", Action.PAY, hasOwner.getThird()) :
											 Trio.of("Street", Action.AUCTION, hasOwner.getThird());
			}
			
		} else if(companies.contains(id)) {
			// Company
			res = Trio.of("Company", Action.PAY, null);
			
		} else if(stations.contains(id)) {
			// Station
			res = Trio.of("Station", Action.PAY, null);
			
		} else if(id.equals(0)) {
			// Start
			res = Trio.of("Start", Action.RECEIVE, null);
			
		} else if(id.equals(10)) {
			// Jail
			// TODO Acción depende de cosas
			res = Trio.of("Jail", Action.NOTHING_HAPPENS, null);
			
		} else if(id.equals(20)) {
			// Parking
			res = Trio.of("Parking", Action.NOTHING_HAPPENS, null);
			
		} else if(id.equals(30)) {
			// Go to jail
			res = Trio.of("GoToJail", Action.GOTOJAIL, null);
			
		}
		return res;
	}
	
}
