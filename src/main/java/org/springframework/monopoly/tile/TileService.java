package org.springframework.monopoly.tile;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.card.Card;
import org.springframework.monopoly.card.CardService;
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
	
	public void setActionTile (Turn turn) {
		Integer gameId = turn.getGame().getId();
		Integer tileId = turn.getFinalTile();
		if (cBoxRepository.findCBByGameId(gameId, tileId).isPresent()) {
			Card card = getCard(turn, "COMMUNITY_BOX");
			turn.setAction(Action.DRAW_CARD);
			turn.setActionCardId(card.getId());
		} else if (gRepository.findGenericByGameId(gameId, tileId).isPresent()) {
			this.genericService.genericTile(turn);
		} else if (luckRepository.findLuckByGameId(gameId, tileId).isPresent()) {
			Card card = getCard(turn, "LUCK");
			turn.setAction(Action.DRAW_CARD);
			turn.setActionCardId(card.getId());
		} else if (taxesService.findTaxesByGameId(gameId, tileId).isPresent()) {
			turn.setAction(Action.PAY_TAX);
		} 
	}
	

	public void calculateActionTile (Turn turn) {
		switch (turn.getAction()) {
			case PAY_TAX: taxesService.payTaxes(turn);
			case DRAW_CARD: cardAction(turn);
			default:;
		}
	}
	

	private void cardAction(Turn turn) {
		Optional<Card> cardOpt = cardService.findCardById(turn.getActionCardId());
		Player player = turn.getPlayer();
		List<Player> players = turn.getGame().getPlayers().stream().filter(p -> !p.equals(player)).collect(Collectors.toList());
		if (cardOpt.isPresent()) {
			Card card = cardOpt.get();
			switch (card.getAction()) {
			case PAY_TAX: cardService.payTax(card, player);
			case PAY_PLAYERS: cardService.payPlayers(card, player, players);
			case CHARGE: cardService.charge(card, player);
			case CHARGE_PLAYERS: cardService.chargePlayers(card, player, players);
			case MOVE: cardService.move(card, player);
			case MOVETO: cardService.moveTo(card, player);
			case REPAIR: cardService.repair(player);
			case GOTOJAIL: cardService.gotoJail(player);
			case SAVE_FREE: cardService.saveFree(player);
			case FREE: cardService.useFree(player);
			default:;
			
			}
		}

		
	}

	public Card getCard(Turn turn, String type) {
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
