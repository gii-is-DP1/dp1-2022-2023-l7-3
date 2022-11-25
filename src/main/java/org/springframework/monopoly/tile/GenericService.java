package org.springframework.monopoly.tile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
	
	private GenericRepository genericRepository;

	@Autowired
	public GenericService(GenericRepository genericRepository) {
		this.genericRepository = genericRepository;
	}
	
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
	
}
