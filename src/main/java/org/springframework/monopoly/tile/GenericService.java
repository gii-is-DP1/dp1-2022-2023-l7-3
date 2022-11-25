package org.springframework.monopoly.tile;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
	
	private GenericRepository genericRepository;

	@Autowired
	public GenericService(GenericRepository genericRepository) {
		this.genericRepository = genericRepository;
	}
	
	//Revisar si cambiar tambien final Tile de turno
	public void genericTile(Turn turn) {
		Player p = turn.getPlayer();
		Optional<Generic> generic = genericRepository.findById(turn.getGame().getId(), turn.getFinalTile());
		if(generic.isPresent()) {
			Generic g = generic.get();
			if(g.getGenericType().equals(GenericType.GOTOJAIL)) {
				p.setIsJailed(true);
				p.setTile(10);
			}
		}
	}
	
}
