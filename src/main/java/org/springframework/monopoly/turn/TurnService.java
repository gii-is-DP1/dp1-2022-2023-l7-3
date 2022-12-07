package org.springframework.monopoly.turn;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
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

	@Autowired
	public TurnService(TurnRepository turnRepository, PropertyService propertyService, TileService tileService) {
		this.turnRepository = turnRepository;
		this.propertyService = propertyService;
		this.tileService = tileService;
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

	//llamada a los dos cosos del set y luego a los dos cosos genericos pa que hagan funciones (chulitas)
	
	public void calculateTurn(Turn turn) {
		
		Pair<Integer, Boolean> roll = getRoll();
		turn.setRoll(roll.getFirst());
		turn.setIsDoubles(roll.getSecond());
		
		// TEMP
//		turn.setRoll(7);
		if(!List.of(2,7,17,22,33,36).contains(turn.getFinalTile())) {
		
		// Llamada al metodo de property
		propertyService.setActionProperty(turn);
		
		// Llamada al metodo del resto de las tiles
		if(turn.getAction() == null) {
			tileService.setActionTile(turn);
		}
		
		} else {
			turn.setAction(Action.DRAW_CARD);
		}
		
		saveTurn(turn);
		
	}
	
	public void evaluateTurn(Turn turn) {
		// Va a depender de si hay pasos intermedios o no
		turn.setIsFinished(true);
		
		// Llamada al metodo de property
//		propertyService.calculateActionProperty(turn);
		
		// Llamada al metodo del resto de las tiles
		if(turn.getAction() == null) {
			
		}
		
		saveTurn(turn);
	}
	
	//el get roll devuelve un integer suma

	public Pair<Integer, Boolean> getRoll() {
		Integer roll1 = random.ints(1, 7).findFirst().getAsInt();
		Integer roll2 = random.ints(1, 7).findFirst().getAsInt();
		return Pair.of(roll1 + roll2, roll1.equals(roll2));
	}
		
}
