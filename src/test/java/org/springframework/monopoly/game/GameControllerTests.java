package org.springframework.monopoly.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.configuration.SecurityConfiguration;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Auction;
import org.springframework.monopoly.property.AuctionRepository;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.tile.CommunityBoxService;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.tile.TileService;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@WebMvcTest(controllers = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTests {
	
	@Autowired
	private GameController gameController;

	@MockBean
	private GameService gameService;
	
	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private TurnService turnService;
	
	@MockBean
	private PropertyService propertyService;
	
	@MockBean
	private TileService tileService;
	
	@MockBean
	private AuctionRepository auctionRepository;
	
	@MockBean
	private TaxesService taxesService;
	
	@MockBean
	private CardService cardService;
	
	@MockBean
	private CommunityBoxService cbService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Game newGame;
	private Model mockModel;
	private Turn mockTurn;
	
	private User admin;
	private Optional<User> adminOptional;
	
	@BeforeEach
	void setup() throws Exception {
		newGame = new Game();
		newGame.setId(-1);
		newGame.setVersion(0);
		
		mockModel = new BindingAwareModelMap();
		
		mockTurn = new Turn();
		mockTurn.setAction(Action.NOTHING_HAPPENS);
		
		mockModel.addAttribute("Turn", mockTurn);
		given(this.gameService.setupGameModel(any(), any(), any())).willReturn(mockModel);
		
		admin = new User();
		admin.setId(-1);
		admin.setEnabled(true);
		admin.setIs_admin("admin");
		admin.setPassword("admin");
		admin.setUsername("admin");
		adminOptional = Optional.of(admin);
		
		given(this.gameService.setUpNewGame(any())).willReturn(null);
		given(this.gameService.setUpNewGamePlayers(any(), any())).willReturn(null);
		given(this.gameService.setProperties(any())).willReturn(null);
		given(this.gameService.saveGame(any())).willReturn(newGame);
		given(this.gameService.findGame(any())).willReturn(Optional.of(newGame));
		
		given(this.userService.findUserByName(anyString())).willReturn(adminOptional);
		
		given(this.turnService.findLastTurn(any())).willReturn(Optional.of(mockTurn));
		given(this.turnService.saveTurn(any())).willReturn(null);
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testShowNewGameView() throws Exception {
		mockMvc.perform(get("/newGame"))
			   		.andExpect(status().isOk())
			   		.andExpect(model().attributeExists("users", "players"))
			   		.andExpect(model().attributeDoesNotExist("error"))
			   		.andExpect(view().name("game/newGame"));
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testPostNewGameSuccess() throws Exception {
		mockMvc.perform(post("/newGame").with(csrf()).param("users[0]", "1").param("users[1]", "2"))
					.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testPostNewGameNotEnoughPlayers() throws Exception {
		mockMvc.perform(post("/newGame").with(csrf()).param("users[0]", "1"))
					.andExpect(model().attributeExists("error"));
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testLoadGame() throws Exception {
		mockMvc.perform(get("/game/2"))
   					.andExpect(status().isOk())
   					.andExpect(view().name("game/gameMain"));
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testLoadGameShouldRedirectToAuction() throws Exception {
		mockModel = new BindingAwareModelMap();
		mockTurn.setAction(Action.AUCTION);
		mockModel.addAttribute("Turn", mockTurn);
		
		mockMvc.perform(get("/game/2"))
   					.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testProcessAuctionFormSuccess() throws Exception { 
		
		Auction testAuction = new Auction(0, List.of(0,1), 100, 10, 12, 2);
		given(this.gameService.saveAuction(any())).willReturn(testAuction);
		given(this.playerService.findPlayerById(anyInt())).willReturn(null);
				
		mockMvc.perform(post("/game/2/auction").with(csrf()).param("playerIndex", "0").param("currentBid", "100")
				.param("remainingPlayers[0]", "0").param("remainingPlayers[1]", "1")
				.param("playerBid", "10").param("propertyId", "12").param("gameId", "2"))
				.andExpect(status().is3xxRedirection()); 
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testProcessEndAuction() throws Exception { 
		
		Auction testAuction = new Auction(0, List.of(0), 100, 10, 12, 2);
		given(this.gameService.saveAuction(any())).willReturn(testAuction);
		given(this.playerService.findPlayerById(anyInt())).willReturn(null);
			
		mockMvc.perform(post("/game/2/auction").with(csrf()).param("playerIndex", "0").param("currentBid", "100")
				.param("remainingPlayers[0]", "0")
				.param("playerBid", "10").param("propertyId", "12").param("gameId", "2"))
				.andExpect(status().isOk())
				.andExpect(view().name("game/gameMain"));
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testEvalTurnShouldChangeTurnAction() throws Exception {
		mockTurn.setAction(Action.BUY);
		
		mockMvc.perform(post("/game/2/tileAction").with(csrf()).param("decisionResult", "false"));
		verify(turnService).saveTurn(any());
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testEvalTurnShouldCallEvaluateTurn() throws Exception {
		mockMvc.perform(post("/game/2/tileAction").with(csrf()).param("decisionResult", "false"));
		verify(turnService).evaluateTurnAction(any(), eq(false));
	}
	
	@WithMockUser(username = "admin", password = "admin", authorities = {"admin"})
	@Test
	void testPostBuildingForm() throws Exception {
		mockMvc.perform(post("/game/2/build").param("house", "1").with(csrf()))
		.andExpect(status().is3xxRedirection());
	}
}
