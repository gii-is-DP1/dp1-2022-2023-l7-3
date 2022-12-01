package org.springframework.monopoly.tile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaxesService {
	
	private TaxesRepository taxesRepository;

	@Autowired
	public TaxesService(TaxesRepository taxesRepository) {
		this.taxesRepository = taxesRepository;
	}
	
	@Transactional
	public void payTaxes(Turn turn) {
		Player p = turn.getPlayer();
		Optional<Taxes> taxes = taxesRepository.findTaxesByGameId(turn.getGame().getId(), turn.getFinalTile());
		if(taxes.isPresent()) {
			p.setMoney(p.getMoney() - taxes.get().getPrice());
		}
	}
	
	@Transactional(readOnly = true)
	public Integer getTaxValue(Turn turn) {
		Optional<Taxes> taxes = taxesRepository.findTaxesByGameId(turn.getGame().getId(), turn.getFinalTile());
		Integer res = null;
		if(taxes.isPresent()) {
			res = taxes.get().getPrice();
		}
		return res;
	}
	
	@Transactional
	public Optional<Taxes> findTaxesByGameId(Integer gameId, Integer tileId) {
		return taxesRepository.findTaxesByGameId(gameId, tileId);
	}
}
