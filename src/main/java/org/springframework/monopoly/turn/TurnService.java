package org.springframework.monopoly.turn;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnService {

	private TurnRepository turnRepository;

	@Autowired
	public TurnService(TurnRepository turnRepository) {
		this.turnRepository = turnRepository;
	}

	@Transactional
	public void saveTurn(Turn turn) throws DataAccessException {
		turnRepository.save(turn);
	}
	
	public Optional<Turn> findTurn(Integer id) {
		return turnRepository.findById(id);
	}
}
