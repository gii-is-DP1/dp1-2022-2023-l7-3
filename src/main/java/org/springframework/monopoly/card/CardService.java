package org.springframework.monopoly.card;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Optional<Card> findCardById(Integer cardId) {
        return cardRepository.findById(cardId) ;
    }
	
	@Transactional
	public List<Card> findTypeCards(CardType cardType) {
        return cardRepository.findAllByCardType(cardType) ;
    }
	
	@Transactional
	public void payTax(Card card, Player player) {
		player.setMoney(player.getMoney() - card.getQuantity());
	}
	
	@Transactional
	public void payPlayers(Card card, Player playerPay, List<Player> playersReceive) {
		
		playerPay.setMoney(playerPay.getMoney() - card.getQuantity()*playersReceive.size());
		
		for (Player p: playersReceive) {
			p.setMoney(p.getMoney() + card.getQuantity());
		}
	}
	
	@Transactional
	public void charge(Card card, Player playerPay) {
		playerPay.setMoney(playerPay.getMoney() + card.getQuantity());
	}
	
	@Transactional
	public void chargePlayers(Card card, Player playerReceive, List<Player> playersPay) {
		
		playerReceive.setMoney(playerReceive.getMoney() + card.getQuantity()*playersPay.size());
		for (Player p: playersPay) {
			p.setMoney(p.getMoney() - card.getQuantity());
		}
	}
	
	@Transactional
	public void move(Card card, Player player) {
		
		Integer newPos = player.getTile() + card.getQuantity();
		
		if (player.getTile() <= 39 && newPos > 39) { 
			player.setTile(newPos%40);
			player.setMoney(player.getMoney() + 200);
		} else {
			player.setTile(newPos);
		}
	}
	
	@Transactional
	public void moveTo(Card card, Player player) {
		player.setTile(card.getQuantity());
	}
	
	@Transactional
	public void repair(Player player) {
		Long numHotels = player.getStreets().stream().filter(street -> street.getHaveHotel()).count();
		Integer numHouses = player.getStreets().stream().mapToInt(street -> street.getHouseNum()).sum();
		Integer hotelMoney = numHotels.intValue() * 25;
		Integer houseMoney = numHouses * 100; 
		
		player.setMoney(player.getMoney() - hotelMoney - houseMoney); 
	}
	
	@Transactional
	public void gotoJail(Player player) {
		player.setTile(10);
		player.setIsJailed(true);
	}
	
	@Transactional
	public void saveFree(Player player) {
		player.setHasExitGate(true);
	}
		
}
