package org.springframework.monopoly.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.tile.CommunityBoxRepository;
import org.springframework.monopoly.tile.CommunityBoxService;
import org.springframework.monopoly.tile.GenericRepository;
import org.springframework.monopoly.tile.GenericService;
import org.springframework.monopoly.tile.LuckRepository;
import org.springframework.monopoly.tile.LuckService;
import org.springframework.monopoly.tile.TaxesRepository;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceSetupGame {
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private CommunityBoxService cbService;
	
	@MockBean
	private LuckService luckService;
	
	@MockBean
	private TaxesService taxService;
	
	@MockBean
	private GenericService genericService;
	
	@Autowired
	protected GameService gameService;
	
	@Autowired
	private CommunityBoxRepository cbRepository;
	
	@Autowired
	private LuckRepository luckRepository;
	
	@Autowired
	private TaxesRepository taxRepository;
	
	@Autowired
	private GenericRepository genericRepository;
	
	private GameForm gameForm;
	
	private Game game1;
	private Game game2;
	
	@BeforeEach
	void setup() {
		gameForm = new GameForm();
		
		User u1 = new User();
		u1.setUsername("testAdmin");
		u1.setEnabled(true);
		u1.setIs_admin("admin");
		u1.setPassword("test1234");
		
		User u2 = new User();
		u2.setUsername("testUser");
		u2.setEnabled(true);
		u2.setIs_admin("user");
		u2.setPassword("test1234");
		
		List<Integer> users = new ArrayList<Integer>();
		users.add(1);
		users.add(2);
		
		given(this.userService.findUser(eq(1))).willReturn(Optional.of(u1));
		given(this.userService.findUser(eq(2))).willReturn(Optional.of(u2));
		
		gameForm.setUsers(users);
		
		game1 = new Game();
		game2 = new Game();
		
		Player p1 = new Player();
		p1.setId(-1);
		p1.setUser(u1);
		
		Player p2 = new Player();
		p2.setId(-2);
		p1.setUser(u2);
		
		game2.setPlayers(Set.of(p1, p2));
		
		given(this.cbService.save(any())).willAnswer(i -> i.getArguments()[0]);
		given(this.luckService.save(any())).willAnswer(i -> i.getArguments()[0]);
		given(this.genericService.save(any())).willAnswer(i -> i.getArguments()[0]);
		given(this.taxService.save(any())).willAnswer(i -> i.getArguments()[0]);
		
		given(this.cbService.getBlankCB()).willReturn(cbRepository.findBlankCommunityBoxes());
		given(this.luckService.getBlankLuck()).willReturn(luckRepository.findBlankLucks());
		given(this.genericService.getBlankGenerics()).willReturn(genericRepository.findBlankGenerics());
		given(this.taxService.getBlankTaxes()).willReturn(taxRepository.findBlankTaxes());
		
	}
	
	@Test
	void setupNewGameTest() throws InvalidNumberOfPLayersException {
		Game g = null;
		g = gameService.setUpNewGame(gameForm);
		
		assertThat(g).isNotNull();
		verify(userService, times(2)).findUser(any());
	}
	
	@Test
	void setupNewGamePlayersTest() {
		Game g = game1;
		g = gameService.setUpNewGamePlayers(List.of(1, 2), g);
		
		assertThat(g.getPlayers()).isNotNull();
		assertThat(g.getPlayers().size() == 2).isTrue();
	}
	
	@Test
	void setupPropertiesTest() {
		Game g = game2;
		g = gameService.setProperties(g);
		
		assertThat(g.getStreets()).isNotNull()
								  .isNotEmpty();
		assertThat(g.getStreets().size() == 22).isTrue();
		
		assertThat(g.getStations()).isNotNull()
		  						  .isNotEmpty();
		assertThat(g.getStations().size() == 4).isTrue();
		
		assertThat(g.getCompanies()).isNotNull()
								  .isNotEmpty();
		assertThat(g.getCompanies().size() == 2).isTrue();
	}
	
	@Test
	void setupTilesTest() {
		Game g = game2;
		gameService.setTiles(g);
		
		verify(cbService, times(3)).save(any());
		verify(luckService, times(3)).save(any());
		verify(genericService, times(4)).save(any());
		verify(taxService, times(2)).save(any());
	}
}
