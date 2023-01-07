package org.springframework.monopoly.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.user.User;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {
	
	@Mock
	private GameRepository gameRepository = mock(GameRepository.class);
	
	@Autowired
	protected GameService gameService;
	
	private List<Game> mockGames;
	
	@BeforeEach 
	public void initMockGames() { // Mock data of user, players and games
		
		mockGames = new ArrayList<>();
		
		User u1 = new User();
		u1.setUsername("testAdmin");
		u1.setEnabled(true);
		u1.setIs_admin("admin");
		u1.setPassword("test1234");
		Player p1 = new Player();
		p1.setId(800);
		p1.setUser(u1);
		
		User u2 = new User();
		u2.setUsername("testUser");
		u2.setEnabled(true);
		u2.setIs_admin("user");
		u2.setPassword("test1234");
		Player p2 = new Player();
		p2.setId(801);
		p1.setUser(u2);
		
		User u3 = new User();
		Player p3 = new Player();
		p3.setId(802);
		p1.setUser(u3);
		
		Game g1 = new Game();
		g1.setId(700);
		g1.setDate(Timestamp.valueOf("2022-11-14 00:00:00"));
		g1.setDuration(60);
		g1.setNumCasas(0);
		Set<Player> pg1 = new HashSet<>();
		pg1.add(p1);
		
		Game g2 = new Game();
		g2.setId(701);
		g2.setDate(Timestamp.valueOf("2022-11-14 00:00:00"));
		g2.setDuration(61);
		g2.setNumCasas(0);
		Set<Player> pg2 = new HashSet<>();
		pg2.add(p2);
		pg2.add(p3);
				
		mockGames.add(g1);
		mockGames.add(g2);
	}
		
	@Test
	void shouldFindGameWithCorrectId() {
		Optional<Game> game1 = this.gameService.findGame(1);
		assertThat(game1.isPresent());
		Game game = game1.get();
		assertThat(game.getDate()).isEqualTo(Timestamp.valueOf("2022-10-11 00:00:00"));
		assertThat(game.getDuration()).isEqualTo(107);
		assertThat(game.getNumCasas()).isEqualTo(0);
	}
	
	@Test
	void shouldNotFindGameWithIncorrectId() {
		Optional<Game> game = this.gameService.findGame(1234567890); // Id does not exist
		assertThat(!game.isPresent());
	}
	
	@Test
	@Transactional
	public void shouldInsertGame() {
		
		Integer lastId = gameRepository.findAll().size()-1;
		
		Game game = new Game();
		game.setDate(Timestamp.valueOf("2022-11-14 00:00:00"));
		game.setDuration(60);
		game.setNumCasas(0);
		
		Set<Player> players = new HashSet<>(List.of(new Player(), new Player() ));
		game.setPlayers(players);
		assertThat(players.size() <= 6);
		
		try {
			this.gameService.saveGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertThat(game.getId()).isNotEqualTo(lastId);
	}
	
	@Test
	public void shouldThrowExceptionInserting() {
		
		Game game = new Game();
		
		Set<Player> players = new HashSet<>(List.of(new Player()));
		game.setPlayers(players);
		Assertions.assertThrows(InvalidNumberOfPLayersException.class, ()->{
			gameService.saveGame(game);
		});
	}
	
	@Test
	@Transactional
	public void getAllGamesAdminPagination() {
		
        lenient().when(gameRepository.findAll()).thenReturn(mockGames);
        Pageable pageRequest = PageRequest.of(0, 2);
        Page<Game> page = gameService.getAll(pageRequest, "testAdmin");
		assertThat(page.hasContent());
		assertThat(page.getNumberOfElements() == 2);
	}
		
	@Test
	@Transactional
	public void getAllGamesUserPagination() {
		
		lenient().when(gameRepository.findAll()).thenReturn(mockGames);
	    Pageable pageRequest = PageRequest.of(0, 2);
	    Page<Game> page = gameService.getAll(pageRequest, "testUser");
	    assertThat(page.hasContent());
	    assertThat(page.getNumberOfElements() == 1); // p2 has only 1 game
	}

	@Test
	public void getPlayersOrderedByTurn() {
		List<Player> players = gameService.getPlayersOrderedByTurn(1);
		Comparator<Player> c = Comparator.comparing(p -> p.getTurnOrder());
		assertThat(players).isSortedAccordingTo(c);
	}
		
}