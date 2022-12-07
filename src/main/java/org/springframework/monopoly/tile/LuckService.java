package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.CardRepository;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LuckService {
	
	private LuckRepository luckRepository;
	
	@Autowired
	public LuckService(LuckRepository luckRepository, CardRepository cardRepository) {
		this.luckRepository = luckRepository;
	}
		
	@Transactional
	public List<Luck> findAll(Turn turn){
		return luckRepository.findAllLuckByGameId(turn.getGame().getId());
	}

	@Transactional
	public Optional<Luck> findById(Turn turn) {
		return luckRepository.findLuckByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
	
	@Transactional(readOnly = true)
	public Set<Luck> getBlankLuck() {
		return luckRepository.findBlankLucks();
	}

	@Transactional
	public Luck save(Luck newLuck) {
		return luckRepository.save(newLuck);
	}
}
