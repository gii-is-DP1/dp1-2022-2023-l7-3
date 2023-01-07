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
		
		roll = Pair.of(3, false);
		
		if(turn.getPlayer().getIsJailed()) {
			turn.setRoll(0);
			turn.setIsDoubles(false);
			turn.setAction(Action.FREE);
			
		} else {
			turn.setRoll(roll.getFirst());
			turn.setIsDoubles(roll.getSecond());
			
			// Llamada al metodo de property
			propertyService.setActionProperty(turn);
			
			// Llamada al metodo del resto de las tiles
			tileService.setActionTile(turn);
		}
		
		saveTurn(turn);
		
	}
	
	@Transactional
	public void evaluateTurnAction(Turn turn, Boolean decisionResult) {
		evaluateTurnAction(turn, decisionResult, null);
	}
	
	@Transactional
	public void evaluateTurnAction(Turn turn, Boolean decisionResult, Integer getOutJailDecision) {
		
		Integer oldPlayerPosition = turn.getPlayer().getTile();
		
		// Call to the method handling properties
		switch(turn.getAction()) {
		case BUY:
		case MORTGAGE:
		case PAY:
			propertyService.calculateActionProperty(turn);
			break;
		default:
			break;
		}
		
		// Call to the method handling the rest of the tiles
		tileService.calculateActionTile(turn, getOutJailDecision);
		
		if(oldPlayerPosition.equals(turn.getPlayer().getTile()) && !turn.getAction().equals(Action.GOTOJAIL)) {
			// Careful here, might set evaluated when nothing happened/ it didnt work
			turn.setIsActionEvaluated(true);
			saveTurn(turn);
		} else {
			/*
			 * If we are here, it means that a card or something moved the player, who landed
			 * on a tile which is not the jail, we need to get them the action of that tile.
			 */
			calculateTurn(turn);
		}
		
		
	}
		
}
