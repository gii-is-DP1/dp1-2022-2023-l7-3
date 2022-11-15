package org.springframework.monopoly.turn;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.property.Property;
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
	public Optional<Turn> findLastTurn(Integer gameId) {
		return turnRepository.findLastTurn(gameId);
	}
	
	public Turn calculateTurn(Turn turn) {
		Pair<Integer, Integer> roll1 = Pair.of(getRoll(), getRoll());
		Integer sumRoll1 = roll1.getFirst() + roll1.getSecond();
		turn.setRoll(sumRoll1);
		Integer tile1 = turn.getInitial_tile() + sumRoll1;
		Trio<String, Action, Integer> action1 = getTileAction(turn, tile1, sumRoll1);

		turn.getActions().add(action1.getSecond());
		turn.getQuantities().add(action1.getThird());
		
		if(action1.getSecond().equals(Action.GOTOJAIL)) {
//			turn.setFinal_tile(10);
//			turn.getPlayer().setIsJailed(true);
			return turn;
		}
		
		if(roll1.getFirst().equals(roll1.getSecond())) {
			turn.getMid_tiles().add(tile1);
			
			Pair<Integer, Integer> roll2 = Pair.of(getRoll(), getRoll());
			Integer sumRoll2 = roll2.getFirst() + roll2.getSecond();
			turn.setRoll(sumRoll1 + sumRoll2);
			Integer tile2 = turn.getInitial_tile() + sumRoll2;
			Trio<String, Action, Integer> action2 = getTileAction(turn, tile2, sumRoll2);

			turn.getActions().add(action2.getSecond());
			turn.getQuantities().add(action2.getThird());
			
			if(action2.getSecond().equals(Action.GOTOJAIL)) {
				return turn;
			}
			
			if(roll2.getFirst().equals(roll2.getSecond())) {
				turn.getMid_tiles().add(tile2);
				
				Pair<Integer, Integer> roll3 = Pair.of(getRoll(), getRoll());
				
				if(roll3.getFirst().equals(roll3.getSecond())) {
					turn.getActions().add(Action.GOTOJAIL);
					turn.getQuantities().add(0);
					return turn;
					
				} else {
					Integer sumRoll3 = roll3.getFirst() + roll3.getSecond();
					turn.setRoll(sumRoll1 + sumRoll2 + sumRoll3);
					Integer tile3 = turn.getInitial_tile() + sumRoll3;
					Trio<String, Action, Integer> action3 = getTileAction(turn, tile3, sumRoll3);
	
					turn.getActions().add(action3.getSecond());
					turn.getQuantities().add(action3.getThird());
					
					if(action3.getSecond().equals(Action.GOTOJAIL)) {
						return turn;
					}
					
					turn.setFinal_tile(tile3);
					
				}
			} else {
				turn.setFinal_tile(tile2);
			}
			
		} else {
			turn.setFinal_tile(tile1);
		}
		
		return turn;
	}
	
	private Integer getRoll() {
		return random.ints(1, 7).findFirst().getAsInt();
	}
	
	// TODO terminar de calcular cosas
	public static Trio<String, Action, Integer> getTileAction(Turn turn, Integer id, Integer tirada) {
		Property property =(Property) propertyService.getProperty(id, turn.getGame().getId());
		Player player = turn.getPlayer();
		Trio<String, Action, Integer> res = null;
		if(streets.contains(id)) {
			// Street
			Trio<Boolean, Boolean, Integer> hasOwner = propertyService.hasOwner(turn, id, tirada);
			if(hasOwner.getFirst()) {
				// Has owner
				res = Trio.of("OwnedStreet", Action.PAY, hasOwner.getThird());
			} else {
				// Ownerless
				res = hasOwner.getSecond() ? Trio.of("Street", Action.PAY, hasOwner.getThird()) :
											 Trio.of("Street", Action.AUCTION, hasOwner.getThird());
			}
			
		} else if(companies.contains(id)) {
			// Company
			res = Trio.of("Company", Action.PAY, propertyService.payPropertyById(property, turn, tirada).getThird());
			
		} else if(stations.contains(id)) {
			// Station
			res = Trio.of("Station", Action.PAY, propertyService.payPropertyById(property, turn, tirada).getThird());
			
		} else if(id.equals(4) || id.equals(38)) {
			// Taxes
			res = Trio.of("Taxes", Action.PAY, null);
			
		} else if(id.equals(0)) {
			// Start
			res = Trio.of("Start", Action.RECEIVE, 200);
			player.setMoney(player.getMoney() + 200);
			
		} else if(id.equals(10)) {
			// Jail (Is in)
			res = Trio.of("Jail", Action.NOTHING_HAPPENS, 0);
			
		} else if(id.equals(20)) {
			// Parking
			res = Trio.of("Parking", Action.NOTHING_HAPPENS, 0);
			
		} else if(id.equals(30)) {
			// Go to jail
			res = Trio.of("GoToJail", Action.GOTOJAIL, 0);
			
		}
		return res;
	}

	
}
