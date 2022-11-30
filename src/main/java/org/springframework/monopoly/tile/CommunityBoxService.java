package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Transactional
	public List<CommunityBox> findAll(Turn turn){
		return communityRepository.findAllCommunityBoxByGameId(turn.getGame().getId());
	}

	@Transactional
	public Optional<CommunityBox> findById(Turn turn) {
		return communityRepository.findCBByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
}
