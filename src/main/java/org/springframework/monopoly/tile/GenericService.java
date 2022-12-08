package org.springframework.monopoly.tile;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerRepository;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenericService {
	
	private GenericRepository genericRepository;
	private PlayerRepository playerRepository;
	private static Random random = new Random();

	@Autowired
	public GenericService(GenericRepository genericRepository, PlayerRepository playerRepository) {
		this.genericRepository = genericRepository;
		this.playerRepository = playerRepository;
	}
	
	@Transactional
	public void genericTile(Turn turn) {
		Optional<Generic> generic = genericRepository.findGenericByGameId(turn.getGame().getId(), turn.getFinalTile());
		if(generic.isPresent()) {
			Boolean goJail = generic.get().getGenericType().equals(GenericType.GOTOJAIL);
			if(goJail) {
				turn.setAction(Action.GOTOJAIL);
			} else {
				turn.setAction(Action.NOTHING_HAPPENS);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public Set<Generic> getBlankGenerics() {
		return genericRepository.findBlankGenerics();
	}

	@Transactional
	public Generic save(Generic newGeneric) {
		return genericRepository.save(newGeneric);
	}
	
	@Transactional
	public void free(Turn turn, Integer decision) {
		
		Player player = turn.getPlayer();
		
		switch (decision) {
			case 1: player.setMoney(player.getMoney() - 50); 
					player.setIsJailed(false);
					break;
			case 2: player.setHasExitGate(false); 
					player.setIsJailed(false);
					break;
			case 3: Pair<Integer, Boolean> roll =  getRoll(); {
				if (roll.getSecond()) {
					player.setIsJailed(false);
					turn.setRoll(roll.getFirst());
				}
			};
			break;
					
			default:}
		playerRepository.save(player);
	}
	
	public Pair<Integer, Boolean> getRoll() {
		Integer roll1 = random.ints(1, 7).findFirst().getAsInt();
		Integer roll2 = random.ints(1, 7).findFirst().getAsInt();
		return Pair.of(roll1 + roll2, roll1.equals(roll2));
	}
	
}