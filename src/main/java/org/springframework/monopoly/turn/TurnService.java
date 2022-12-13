package org.springframework.monopoly.turn;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.tile.TileService;
import org.springframework.monopoly.util.RollGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnService {
	
	private TurnRepository turnRepository;
	private PropertyService propertyService;
	private TileService tileService;

	@Autowired
	public TurnService(TurnRepository turnRepository, PropertyService propertyService, TileService tileService) {
		this.turnRepository = turnRepository;
		this.propertyService = propertyService;
		this.tileService = tileService;
	}

	@Transactional
	public Turn saveTurn(Turn turn) throws DataAccessException {
		return turnRepository.save(turn);
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
		
		Pair<Integer, Boolean> roll = RollGenerator.getRoll();
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
	public void evaluateTurnAction(Turn turn, Boolean decisionResult) {
		// Call to the method handling properties
		
		switch(turn.getAction()) {
		case BUY:
			propertyService.calculateActionProperty(turn);
			break;
		case PAY:
			propertyService.calculateActionProperty(turn);
			break;
		default:
			break;
		}
		
		// Call to the method handling the rest of the tiles
		tileService.calculateActionTile(turn, null);
		
		// Careful here, might set evaluated when nothing happened/ it didnt work
		turn.setIsActionEvaluated(true);
		saveTurn(turn);
	}
		
}
