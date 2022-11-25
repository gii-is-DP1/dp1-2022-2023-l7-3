package org.springframework.monopoly.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.Card;
import org.springframework.monopoly.card.CardRepository;
import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoxService {
	
	private CommunityBoxRepository communityRepository;
	private CardRepository cardRepository;
	
	@Autowired
	public CommunityBoxService(CommunityBoxRepository communityRepository, CardRepository cardRepository) {
		this.communityRepository = communityRepository;
		this.cardRepository = cardRepository;
	}
	
	//Acción de turno
	public Card getCommunityCard(Turn turn) {
		Random rand = new Random();
		List<Card> communityCards = cardRepository.findAllByCardType("COMMUNITY_CARD");
		for(Player p : turn.getGame().getPlayers()) {
			if(p.getHasExitGate()) {
				communityCards = communityCards.stream().filter(x-> !x.getAction().equals(Action.FREE)).collect(Collectors.toList());
			}
		}
		Card card =  communityCards.get(rand.nextInt((communityCards.size())));
		return card;
	}
	
	
	public List<CommunityBox> findAll(Turn turn){
		return communityRepository.findAllCommunityBoxByGameId(turn.getGame().getId());
	}
	
	public CommunityBox findById(Turn turn) {
		return communityRepository.findCBByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
}
