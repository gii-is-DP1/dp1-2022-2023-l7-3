package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.CardRepository;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoxService {
	
	private CommunityBoxRepository communityRepository;

	@Autowired
	public CommunityBoxService(CommunityBoxRepository communityRepository, CardRepository cardRepository) {
		this.communityRepository = communityRepository;
	}
	
	public List<CommunityBox> findAll(Turn turn){
		return communityRepository.findAllCommunityBoxByGameId(turn.getGame().getId());
	}

	public Optional<CommunityBox> findById(Turn turn) {
		return communityRepository.findCBByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
}
