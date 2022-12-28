package org.springframework.monopoly.turn;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.game.GameService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TurnServiceTests {
	
	@Autowired
	TurnService turnService;
	
	@Autowired
	GameService gameService;
	
	Turn sampleTurn;
	
	@BeforeEach
	public void setup() {
		Game game = gameService.findGame(1).get();
		
		sampleTurn = new Turn();
		sampleTurn.setGame(game);
		sampleTurn.setInitial_tile(0);
		sampleTurn.setIsDoubles(false);
		sampleTurn.setRoll(9);
		sampleTurn.setPlayer(game.getPlayers().stream().filter(p -> p.getId() == 3).findFirst().get());
	}
	
	@Test
	void shouldFindLastTurn() {
		Optional<Turn> lastTurn = turnService.findLastTurn(1);
		
		assertThat(lastTurn.isPresent()).isTrue();
		assertThat(lastTurn.get().getTurnNumber()).isEqualTo(2);
	}
	
	@Test
	void shouldSaveTurn() {
		Turn savedTurn = turnService.saveTurn(sampleTurn);
		
		assertThat(savedTurn).isNotNull();
	}
	
	@Test
	void shouldFindTurn() {
		Optional<Turn> turn = turnService.findTurn(0);
		assertThat(turn.isPresent()).isTrue();
	}
	
	@Test 
	void shouldCalculateTurnAction() {
		turnService.calculateTurn(sampleTurn);
		
		assertThat(sampleTurn.getAction()).isNotNull();
	}
	
	@Test
	void shouldEvaluateTurnAction() {
		turnService.evaluateTurnAction(sampleTurn, null);
		
		assertThat(sampleTurn.getIsActionEvaluated()).isTrue();
	}
	
}
