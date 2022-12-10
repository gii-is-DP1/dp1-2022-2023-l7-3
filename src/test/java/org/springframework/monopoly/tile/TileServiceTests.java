package org.springframework.monopoly.tile;

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
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;


@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TileServiceTests {
	
	
	@Autowired
	protected TileService tileService;
	private static Player player;
	private static Game game;
	private  List<Turn> turns;
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public TileServiceTests(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@BeforeEach
	void setup() {
		this.turns = new ArrayList<>();
		game = new Game();
		game.setId(2);
		
		player = playerRepository.findPlayerById(5, 2);
		player.setIsJailed(true);
		player.setMoney(1000);
		player.setHasExitGate(true);
		
		Turn turn = new Turn();
		turn.setAction(null);
		turn.setActionCardId(null);
		turn.setId(10003);
		turn.setPlayer(player);
		turn.setInitial_tile(0);
		turn.setRoll(null);
		turn.setGame(game);
		
		turns.add(turn);
		
		Turn turn2 = new Turn();
		turn2.setAction(null);
		turn2.setActionCardId(null);
		turn2.setId(10004);
		turn2.setPlayer(player);
		turn2.setInitial_tile(0);
		turn2.setRoll(null);
		turn2.setGame(game);
		
		turns.add(turn2);
		
		Turn turn3 = new Turn();
		turn3.setAction(null);
		turn3.setActionCardId(null);
		turn3.setId(10005);
		turn3.setPlayer(player);
		turn3.setInitial_tile(0);
		turn3.setRoll(null);
		turn3.setGame(game);
		
		turns.add(turn3);
	}
	

	@Test
	void shouldExitGateWithMoney() {
		Turn currentTurn = turns.get(2);
		currentTurn.setAction(Action.FREE);
		tileService.calculateActionTile(currentTurn, 1);
		assertThat(currentTurn.getPlayer().getIsJailed()).isEqualTo(false);
		assertThat(currentTurn.getPlayer().getMoney()).isEqualTo(950);

	}
	
	@Test
	void shouldExitGateWithCard() {
		Turn currentTurn = turns.get(2);
		currentTurn.setAction(Action.FREE);
		tileService.calculateActionTile(currentTurn, 2);
		assertThat(currentTurn.getPlayer().getIsJailed()).isEqualTo(false);
		assertThat(currentTurn.getPlayer().getMoney()).isEqualTo(1000);
		assertThat(currentTurn.getPlayer().getHasExitGate()).isEqualTo(false);

	}
	
	@Test
	void shouldNotExitGateWithCard() {
		Turn currentTurn = turns.get(2);
		currentTurn.setAction(Action.FREE);
		player.setHasExitGate(false);
		tileService.calculateActionTile(currentTurn, 2);
		assertThat(currentTurn.getPlayer().getIsJailed()).isEqualTo(true);
		assertThat(currentTurn.getPlayer().getMoney()).isEqualTo(1000);
		assertThat(currentTurn.getPlayer().getHasExitGate()).isEqualTo(false);

	}
	
	@Test
	void shouldExitGateWithDoubles() {
		Turn currentTurn = turns.get(2);
		currentTurn.setAction(Action.FREE);
		tileService.calculateActionTile(currentTurn, 3);
		assertThat(currentTurn.getPlayer().getIsJailed()).isEqualTo(false);
		if(currentTurn.getIsDoubles()) {
			assertThat(currentTurn.getPlayer().getMoney()).isEqualTo(1000);
		}else {
			assertThat(currentTurn.getPlayer().getMoney()).isEqualTo(950);
		}

	}

		
}