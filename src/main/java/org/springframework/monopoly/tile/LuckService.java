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
public class LuckService {
	
	private LuckRepository luckRepository;
	private CardRepository cardRepository;
	
	@Autowired
	public LuckService(LuckRepository luckRepository, CardRepository cardRepository) {
		this.luckRepository = luckRepository;
		this.cardRepository = cardRepository;
	}
	
	public Card getCommunityCard(Game game) {
		Random rand = new Random();
		List<Card> ls = cardRepository.findAllByCardType("LUCK");
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
