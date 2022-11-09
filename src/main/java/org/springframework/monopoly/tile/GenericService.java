package org.springframework.monopoly.tile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
	
	private GenericRepository genericRepository;

	@Autowired
	public GenericService(GenericRepository genericRepository) {
		this.genericRepository = genericRepository;
	}
	
	public void genericTile(Player p, Integer id) {
		Optional<Generic> generic = genericRepository.findById(id);
		if(generic.isPresent()) {
			Generic g = generic.get();
			if(g.getGenericType().equals(GenericType.GOTOJAIL)) {
				p.setIsJailed(true);
				p.setTile(10);
			}
		}
	}
	
}
