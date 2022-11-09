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
	
	public Card getCommunityCard(Game game) {
		Random rand = new Random();
		List<Card> ls = cardRepository.findAllByCardType("COMMUNITY_CARD");
		List<Card> ls2 = new ArrayList<Card>(ls);
		for(Player p : game.getPlayers()) {
			if(p.getHasExitGate()) {
				ls2 = ls.stream().filter(x-> !x.getAction().equals(Action.FREE)).collect(Collectors.toList());
			}
		}
		Card card =  ls2.get(rand.nextInt((ls2.size() - 0)));
		return card;
		
	}
}
