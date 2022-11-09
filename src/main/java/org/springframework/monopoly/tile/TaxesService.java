package org.springframework.monopoly.tile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Service;

@Service
public class TaxesService {
	private TaxesRepository taxesRepository;

	@Autowired
	public TaxesService(TaxesRepository taxesRepository) {
		this.taxesRepository = taxesRepository;
	}
	
	public void payTaxes(Player p, Integer id) {
		Optional<Taxes> taxes = taxesRepository.findById(id);
		if(taxes.isPresent()) {
			p.setMoney(p.getMoney() - taxes.get().getPrice());
		}
	}
}
