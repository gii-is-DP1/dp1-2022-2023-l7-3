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
public class GameServiceSetupModelTest {
	
	@Mock
	private Authentication auth = mock(Authentication.class);
	
	@Autowired
	protected GameService gameService;
	
	@BeforeEach
	void setup() {
		when(this.auth.getName()).thenReturn("xXPaco02Xx");
	}
	
	@Test
	void setupGameModelTest() throws Exception {
		Model model = new BindingAwareModelMap();
		
		model = gameService.setupGameModel(model, 2, auth);
		
		assertThat(model.getAttribute("Game")).isNotNull();
		assertThat(model.getAttribute("Turn")).isNotNull();
		assertThat(model.getAttribute("Players")).isNotNull();
		assertThat(model.getAttribute("Version")).isNotNull();
		assertThat(model.getAttribute("property")).isNotNull();
		assertThat(model.getAttribute("isPlaying")).isNotNull();
		assertThat(model.getAttribute("Properties")).isNotNull();
		assertThat(model.getAttribute("Colors")).isNotNull();
	}
	
}
