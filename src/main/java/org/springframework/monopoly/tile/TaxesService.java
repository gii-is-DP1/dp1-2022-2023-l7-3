package org.springframework.monopoly.tile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
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
	public void payTaxes(Player p, Integer id) {
		Optional<Taxes> taxes = taxesRepository.findById(id);
		if(taxes.isPresent()) {
			p.setMoney(p.getMoney() - taxes.get().getPrice());
		}
	}
	
	@Transactional(readOnly = true)
	public Integer getTaxValue(Integer id) {
		Optional<Taxes> taxes = taxesRepository.findById(id);
		Integer res = null;
		if(taxes.isPresent()) {
			res = taxes.get().getPrice();
		}
		return res;
	}
}
