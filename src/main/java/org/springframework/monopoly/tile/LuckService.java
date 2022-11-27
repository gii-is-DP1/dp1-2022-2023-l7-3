package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.CardRepository;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class LuckService {
	
	private LuckRepository luckRepository;
	
	@Autowired
	public LuckService(LuckRepository luckRepository, CardRepository cardRepository) {
		this.luckRepository = luckRepository;
	}
		
	public List<Luck> findAll(Turn turn){
		return luckRepository.findAllLuckByGameId(turn.getGame().getId());
	}

	public Optional<Luck> findById(Turn turn) {
		return luckRepository.findLuckByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
}
