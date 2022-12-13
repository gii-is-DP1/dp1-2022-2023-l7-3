package org.springframework.monopoly.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerRepository;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CardServiceTests {
	
	@Autowired
	protected CardService cardService;
	private static Player player;
	private static Game game;
	private  List<Player> players;
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public CardServiceTests(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@BeforeEach
	void setup() {
		this.players = new ArrayList<>();
		game = new Game();
		game.setId(2);
		
		player = playerRepository.findPlayerById(5, 2);
		player.setIsJailed(true);
		player.setMoney(1000);
		player.setHasExitGate(true);
		player.setTile(20);
		
		Player player2 = playerRepository.findPlayerById(4, 2);
		player2.setIsJailed(false);
		player2.setMoney(1000);
		player2.setHasExitGate(true);
		
		Player player3 = playerRepository.findPlayerById(7, 2);
		player3.setIsJailed(false);
		player3.setMoney(1000);
		player3.setHasExitGate(true);

		players.add(player2);
		players.add(player3);
	}

	@Test
	void shouldPayTax() {
		Card cardPayTax = cardService.findCardById(2).get(); 
		cardService.payTax(cardPayTax, player);
		assertThat(player.getMoney()).isEqualTo(850);
	}
	
	@Test
	void shouldChargeMoney() {
		Card cardChargeMoney = cardService.findCardById(1).get();
		cardService.charge(cardChargeMoney, player);
		assertThat(player.getMoney()).isEqualTo(1050);
	}
	
	@Test
	void shouldChargePlayers() {
		Card cardChargePlayers = cardService.findCardById(18).get();
		cardService.chargePlayers(cardChargePlayers, player, players);
		assertThat(player.getMoney()).isEqualTo(1020);
		
		for (Player p : players) {
			assertThat(p.getMoney()).isEqualTo(990);
		}	
	}
	
	@Test
	void shouldMove() {
		Card cardMove = cardService.findCardById(11).get();
		cardService.move(cardMove, player);
		assertThat(player.getTile()).isEqualTo(17); 
	}
	
	@Test
	void shouldMoveTo() {
		Card cardMoveTo = cardService.findCardById(10).get();
		cardService.moveTo(cardMoveTo, player);
		assertThat(player.getTile()).isEqualTo(24);
	}
	
	@Test 
	void shouldRepair() {
		cardService.repair(player); // Player has 10 houses and 2 hotels
		assertThat(player.getMoney()).isEqualTo(550); // 100 - (10*25) - (2*100)
	}
	
	@Test 
	void shouldGoToJail() {
		cardService.gotoJail(player);
		assertThat(player.getTile()).isEqualTo(10);
		assertThat(player.getIsJailed()).isEqualTo(true);
	}
	
	@Test 
	void shouldSaveFree() {
		cardService.saveFree(player);
		assertThat(player.getHasExitGate()).isEqualTo(true);
	}
		
}
