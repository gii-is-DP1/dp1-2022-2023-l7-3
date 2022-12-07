package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.Card;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.card.CardType;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class TileService {
	
	private CommunityBoxRepository cBoxRepository;
	private GenericRepository gRepository;
	private LuckRepository luckRepository;
	private CardService cardService;
	private TaxesService taxesService;
	private GenericService genericService;
	
	@Autowired
	public TileService(CommunityBoxRepository cBoxRepository, GenericRepository gRepository,
			LuckRepository luckRepository, CardService cardService, 
			GenericService genericService, TaxesService taxesService) {
		
		this.cBoxRepository = cBoxRepository;
		this.gRepository = gRepository;
		this.luckRepository = luckRepository;
		this.genericService = genericService;
		this.taxesService = taxesService;
		this.cardService = cardService;
	}
	
	@Transactional
	public void setActionTile (Turn turn) {
		Integer gameId = turn.getGame().getId();
		Integer tileId = turn.getFinalTile();
		if (cBoxRepository.findCBByGameId(gameId, tileId).isPresent()) {
			Card card = getCard(turn, CardType.COMMUNITY_CARD);
			turn.setAction(Action.DRAW_CARD);
			turn.setActionCardId(card.getId());
		} else if (gRepository.findGenericByGameId(gameId, tileId).isPresent()) {
			this.genericService.genericTile(turn);
		} else if (luckRepository.findLuckByGameId(gameId, tileId).isPresent()) {
			Card card = getCard(turn, CardType.LUCK);
			turn.setAction(Action.DRAW_CARD);
			turn.setActionCardId(card.getId());
		} else if (taxesService.findTaxesByGameId(gameId, tileId).isPresent()) {
			turn.setAction(Action.PAY_TAX);
		} 
	}

	public void calculateActionTile (Turn turn, Integer decision) {
		switch (turn.getAction()) {
			case PAY_TAX: 
				taxesService.payTaxes(turn);
				break;
			case DRAW_CARD: 
				cardAction(turn);
				break;
			case FREE: 
				genericService.free(turn, decision);
				break;
			default:;
		}
	}
	
	@Transactional
	private void cardAction(Turn turn) {
		
		Optional<Card> cardOpt = cardService.findCardById(turn.getActionCardId());
		Player player = turn.getPlayer();
		List<Player> players = turn.getGame().getPlayers().stream().filter(p -> !p.equals(player)).collect(Collectors.toList());
		
		if (cardOpt.isPresent()) {
			Card card = cardOpt.get();
			switch (card.getAction()) {
			case PAY_TAX: 
				cardService.payTax(card, player);
				break;
			case PAY_PLAYERS: 
				cardService.payPlayers(card, player, players);
				break;
			case CHARGE: 
				cardService.charge(card, player);
				break;
			case CHARGE_PLAYERS: 
				cardService.chargePlayers(card, player, players);
				break;
			case MOVE: 
				cardService.move(card, player);
				break;
			case MOVETO: 
				cardService.moveTo(card, player);
				break;
			case REPAIR: 
				cardService.repair(player);
				break;
			case GOTOJAIL: 
				cardService.gotoJail(player);
				break;
			case SAVE_FREE: 
				cardService.saveFree(player);
				break;
			default:;
			
			}
		}
	}

	public Card getCard(Turn turn, CardType type) {
		Random rand = new Random();
		List<Card> communityCards = cardService.findTypeCards(type);
		for(Player p : turn.getGame().getPlayers()) {
			if(p.getHasExitGate()) {
				communityCards = communityCards.stream().filter(x-> !x.getAction().equals(Action.FREE)).collect(Collectors.toList());
			}
		}
		Card card =  communityCards.get(rand.nextInt((communityCards.size())));
		return card;
	}
	
}
