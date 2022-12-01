package org.springframework.monopoly.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.game.GameRepository;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;


@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PropertyServiceTests {

	@Autowired
	protected PropertyService propertyService;
	@Mock
	private Turn mockTurn;
	@Mock
	private Player mockPlayer;
	@Mock
	private Game mockGame;
	@Mock
	private CompanyRepository companyRepository = mock(CompanyRepository.class);
	@Mock
	private StreetRepository streetRepository = mock(StreetRepository.class);
	@Mock
	private StationRepository stationRepository = mock(StationRepository.class);
	
	@BeforeEach
	void setup() {
		mockGame = new Game();
		mockGame.setId(2);
		mockGame.setCompanies((List<Company>) this.companyRepository.findAll());
		mockGame.setStreets((List<Street>) this.streetRepository.findAll());
		mockGame.setStations((List<Station>) this.stationRepository.findAll());
		
		mockPlayer = new Player();
		mockPlayer.setId(10002);
		mockPlayer.setMoney(300);
		mockPlayer.setGame(mockGame);
		
		mockTurn = new Turn();
		mockTurn.setAction(null);
		mockTurn.setActionCardId(null);
		mockTurn.setId(10003);
		mockTurn.setPlayer(mockPlayer);
		mockTurn.setInitial_tile(0);
		mockTurn.setRoll(39);
		
		mockTurn.setGame(mockGame);
	}
	
	@Test
	void shouldBuyProperty() {
		this.propertyService.setActionProperty(mockTurn);
//		this.propertyService.calculateActionProperty(mockTurn,null);
//		assertThat(mockTurn.getPlayer().getMoney()).isEqualTo(200);
		assertThat(mockTurn.getAction()).isEqualTo(Action.AUCTION);
		
		
		
		
	}
	
	
	
}
