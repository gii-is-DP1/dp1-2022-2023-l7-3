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
public class LuckService {
	
	private LuckRepository luckRepository;
	private CardRepository cardRepository;
	
	@Autowired
	public LuckService(LuckRepository luckRepository, CardRepository cardRepository) {
		this.luckRepository = luckRepository;
		this.cardRepository = cardRepository;
	}
	
	//Acción de turno
	public Card getCommunityCard(Turn turn) {
		Random rand = new Random();
		List<Card> luckCards = cardRepository.findAllByCardType("LUCK");
		for(Player p : turn.getGame().getPlayers()) {
			if(p.getHasExitGate()) {
				luckCards = luckCards.stream().filter(x-> !x.getAction().equals(Action.FREE)).collect(Collectors.toList());
			}
		}
		Card card =  luckCards.get(rand.nextInt((luckCards.size())));
		return card;
	}
	
	
	public List<Luck> findAll(Turn turn){
		return luckRepository.findAllLuckByGameId(turn.getGame().getId());
	}
	
	public Luck findById(Turn turn) {
		return luckRepository.findLuckByGameId(turn.getGame().getId(), turn.getFinalTile());
	}
}
