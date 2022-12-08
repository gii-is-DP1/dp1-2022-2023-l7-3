package org.springframework.monopoly.turn;


import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.tile.TileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnService {
	
	private static Random random = new Random();
	
	private TurnRepository turnRepository;
	private PropertyService propertyService;
	private TileService tileService;
	private PlayerService playerService;

	@Autowired
	public TurnService(TurnRepository turnRepository, PropertyService propertyService, TileService tileService, PlayerService playerService) {
		this.turnRepository = turnRepository;
		this.propertyService = propertyService;
		this.tileService = tileService;
		this.playerService = playerService;
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

	@Transactional
	public void calculateTurn(Turn turn) {
		
		Pair<Integer, Boolean> roll = getRoll();
		turn.setRoll(roll.getFirst());
		turn.setIsDoubles(roll.getSecond());
		
		// TEMP
//		turn.setRoll(7);
		
		// Llamada al metodo de property
		propertyService.setActionProperty(turn);
		
		// Llamada al metodo del resto de las tiles
		if(turn.getAction() == null) {
			tileService.setActionTile(turn);
		}
		
		saveTurn(turn);
		
	}
	
	@Transactional
	public void evaluateTurn(Turn turn, Boolean formValue) {
		// Llamada al metodo de property
		if(formValue || turn.getAction().equals(Action.PAY)) {
			propertyService.calculateActionProperty(turn);
		}
		
		// Llamada al metodo del resto de las tiles
		tileService.calculateActionTile(turn, null);
		
		// Saving again just in case some method forgot earlier
		playerService.savePlayer(turn.getPlayer());
		
		turn.setIsActionEvaluated(true);
		saveTurn(turn);
	}
	
	public Pair<Integer, Boolean> getRoll() {
		Integer roll1 = random.ints(1, 7).findFirst().getAsInt();
		Integer roll2 = random.ints(1, 7).findFirst().getAsInt();
		return Pair.of(roll1 + roll2, roll1.equals(roll2));
	}
		
}
