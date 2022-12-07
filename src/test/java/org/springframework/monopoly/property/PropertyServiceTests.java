package org.springframework.monopoly.property;

import static org.assertj.core.api.Assertions.assertThat;



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

import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;


@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PropertyServiceTests {

	@Autowired
	protected PropertyService propertyService;
	private static Turn turn;
	private static Player player;
	private static Game game;
	
	private PlayerRepository playerRepository;
	@Autowired
	public PropertyServiceTests(PlayerRepository playerRepository) {
		
		this.playerRepository = playerRepository;
	}	
	@BeforeEach
	void setup() {
		game = new Game();
		game.setId(2);
		
		player = playerRepository.findPlayerById(5, 2);
		
		turn = new Turn();
		turn.setAction(null);
		turn.setActionCardId(null);
		turn.setId(10003);
		turn.setPlayer(player);
		turn.setInitial_tile(0);
		turn.setRoll(null);
		
		turn.setGame(game);
	}
	
	@Test
	void shouldBuyProperty() {
		turn.setRoll(11);
		this.propertyService.setActionProperty(turn);
		assertThat(turn.getAction()).isEqualTo(Action.BUY);
		this.propertyService.calculateActionProperty(turn);
		assertThat(turn.getPlayer().getMoney()).isEqualTo(437);
		Property property = (Property)this.propertyService.getProperty(turn.getFinalTile(), 2);
		assertThat(property.getOwner()).isEqualTo(turn.getPlayer());
	}

	@Test
	void shouldNotBuyProperty() {
		turn.setRoll(11);
		player.setMoney(2);
		this.propertyService.setActionProperty(turn);
		assertThat(turn.getAction()).isEqualTo(Action.AUCTION); //the player has no money so the property is auctioned
	}

	@Test
	void shouldPayProperty() {
		turn.setRoll(8);
		this.propertyService.setActionProperty(turn);
		assertThat(turn.getAction()).isEqualTo(Action.PAY);
		this.propertyService.calculateActionProperty(turn);
		assertThat(turn.getPlayer().getMoney()).isEqualTo(487);
		Property property = (Property)this.propertyService.getProperty(turn.getFinalTile(), 2);
		assertThat(property.getOwner().getMoney()).isEqualTo(1583);
	}

	@Test
	void shouldNotPayProperty() {
		turn.setRoll(6);
		player.setMoney(2);
		this.propertyService.setActionProperty(turn);
		assertThat(turn.getAction()).isEqualTo(Action.MORTGAGE); //the player has no money so the property is mortgaged
	}
	
	
	
}