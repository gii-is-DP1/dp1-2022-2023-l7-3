package org.springframework.monopoly.game;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.configuration.SecurityConfiguration;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.CompanyService;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@WebMvcTest(controllers = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTests {
	
	@Autowired
	private GameController gameController;

	@MockBean
	private GameService gameService;

	@MockBean
	private UserService userService;
	
	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private TurnService turnService;
	
	@MockBean
	private StreetService streetService;
	
	@MockBean
	private CompanyService companyService;
	
	@Autowired
	private MockMvc mockMvc;

	private Game mockGame;
	
}
