package org.springframework.monopoly.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Service;

@Service
public class CardService {
	
	private CardRepository cardRepository;
	
	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	@Transactional
	public void saveCard(Card card) throws DataAccessException {
		cardRepository.save(card);
	}
	
	// El metodo findTypeCards devuelve las cartas segun el tipo que le pongas (LUCK o COMMUNITY_CARD)
	
	public List<Card> findTypeCards(String cardType) {
		
		Iterator<Card> it = cardRepository.findAll().iterator();
		List<Card> typeCards = new ArrayList<>();
		
		while(it.hasNext()) {
			if (it.next().getCardType().toString().equals(cardType)) {
				typeCards.add(it.next());
			}
		}
		return typeCards;
	}
	
	public void pay(Card card, Player player) {
		
		player.setMoney(player.getMoney() - card.getQuantity());
	}
	
	public void payPlayers(Card card, Player playerPay, List<Player> playersReceive) {
		
		playerPay.setMoney(playerPay.getMoney() - card.getQuantity()*playersReceive.size());
		for (Player p: playersReceive) {
			p.setMoney(p.getMoney() + card.getQuantity());
		}
	}
	
	public void charge(Card card, Player playerPay) {
		
		playerPay.setMoney(playerPay.getMoney() + card.getQuantity());
	}
	
	public void chargePlayers(Card card, Player playerReceive, List<Player> playersPay) {
		
		playerReceive.setMoney(playerReceive.getMoney() + card.getQuantity()*playersPay.size());
		for (Player p: playersPay) {
			p.setMoney(p.getMoney() - card.getQuantity());
		}
	}
	
	public void move(Card card, Player player) {
		
		player.setTile(player.getTile() + card.getQuantity()); // Controlar que pasa por salida (if)
	}
	
	public void moveTo(Card card, Player player) {
			
		player.setTile(card.getQuantity());
	}
	
	public void repair(Player player) {
		
		player.setMoney(player.getMoney()); // Acabar
	}
	
	
	
	
}
