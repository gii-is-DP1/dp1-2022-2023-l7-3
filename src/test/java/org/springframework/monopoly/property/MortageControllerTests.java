package org.springframework.monopoly.property;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.configuration.SecurityConfiguration;
import org.springframework.monopoly.exceptions.MortgageHousesNotUniform;
import org.springframework.monopoly.game.GameController;
import org.springframework.monopoly.game.GameService;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.tile.CommunityBoxService;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.tile.TileService;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = GameController.class, 
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration = SecurityConfiguration.class)
public class MortageControllerTests {
	
	@Autowired
	private GameController gameController;
	
	@MockBean
	private GameService gameService;
	
	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private UserService userService;
	
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
	
	@MockBean
	private TurnService turnService;
	
	@MockBean
	private PropertyService propertyService;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	Player player;
	
	@BeforeEach
	void setup() {
		Turn turn = new Turn();
		turn.setIsFinished(false);
		
		User user = new User();
		user.setId(-1);
		
		player = new Player();
		player.setId(5);
		player.setUser(user);
		turn.setPlayer(player);
		
		when(userService.findUserByName(any())).thenReturn(Optional.of(user));
		when(turnService.findLastTurn(any())).thenReturn(Optional.of(turn));
		
	}

	@WithMockUser(username = "CryptoBro64", password = "cryptobro")
	@Test
	void testNoPropertySelected() throws Exception {
		mockMvc.perform(post("/game/2/mortgage").with(csrf()).param("propertyId", "-1"))
			   		.andExpect(model().attributeHasFieldErrorCode("MortgagePropertyForm", "propertyId", "NoPropertySelected"))
			   		.andExpect(view().name("game/gameMain"));
	}
	
	@WithMockUser(username = "CryptoBro64", password = "cryptobro")
	@Test
	void testPropertyNotOwned() throws Exception {
		Street street = new Street();
		street.setOwner(new Player());
		street.setIsMortage(false);
		
		when(propertyService.getProperty(6, 2)).thenReturn(street);
		
		mockMvc.perform(post("/game/2/mortgage").with(csrf()).param("propertyId", "6"))
			   		.andExpect(model().attributeHasFieldErrorCode("MortgagePropertyForm", "propertyId", "PropertyNotOwned"))
			   		.andExpect(view().name("game/gameMain"));
	}
	
	@WithMockUser(username = "CryptoBro64", password = "cryptobro")
	@Test
	void testHouseMortgageNotUniform() throws Exception {
		Street street = new Street();
		street.setOwner(player);
		street.setIsMortage(false);
		
		when(propertyService.getProperty(3, 2)).thenReturn(street);
		
		doThrow(new MortgageHousesNotUniform()).when(propertyService).mortgageProperty(any(), any(), any());
		
		mockMvc.perform(post("/game/2/mortgage").with(csrf()).param("propertyId", "3"))
			   		.andExpect(model().attributeHasFieldErrorCode("MortgagePropertyForm", "propertyId", "HouseMortgageNotUniform"))
			   		.andExpect(view().name("game/gameMain"));
	}
		
	@WithMockUser(username = "CryptoBro64", password = "cryptobro")
	@Test
	void testShouldMortgage() throws Exception {
		Street street = new Street();
		street.setOwner(player);
		street.setIsMortage(false);
		
		when(propertyService.getProperty(31, 2)).thenReturn(street);
		
		mockMvc.perform(post("/game/2/mortgage").with(csrf()).param("propertyId", "31"))
			   		.andExpect(model().hasNoErrors())
			   		.andExpect(view().name("game/gameMain"));
		verify(propertyService).mortgageProperty(any(), any(), any());
	}
	
	@WithMockUser(username = "CryptoBro64", password = "cryptobro")
	@Test
	void testPropertyAlreadyMortgaged() throws Exception {
		Street street = new Street();
		street.setOwner(player);
		street.setIsMortage(true);
		
		when(propertyService.getProperty(5, 2)).thenReturn(street);
		
		mockMvc.perform(post("/game/2/mortgage").with(csrf()).param("propertyId", "5"))
			   		.andExpect(model().attributeHasFieldErrorCode("MortgagePropertyForm", "propertyId", "PropertyAlreadyMortgaged"))
			   		.andExpect(view().name("game/gameMain"));
	}
	
}
