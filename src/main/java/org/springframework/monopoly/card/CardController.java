package org.springframework.monopoly.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class CardController {
	
	private CardService cardService;

	@Autowired
	public CardController (CardService cardService) {
		this.cardService = cardService;
	}
	
}
