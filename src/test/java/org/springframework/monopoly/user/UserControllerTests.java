package org.springframework.monopoly.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTests {
	
	private static final int TEST_OWNER_ID = 1000;
	
	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private User mockUser;
	
	@BeforeEach
	void setup() {

		mockUser = new User();
		mockUser.setId(TEST_OWNER_ID);
		mockUser.setIs_admin("FALSE");
		mockUser.setPassword("12345");
		mockUser.setUsername("testUser");
		mockUser.setEnabled(true);
		mockUser.setPlayer(Set.of());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitFindUsersSucess() throws Exception {
		
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.getAll(p);
		given(this.userService.getAll(p)).willReturn(page);
		mockMvc.perform(get("/monopolyUsers/list")).andExpect(result -> result.getModelAndView().getViewName().equals("users/monopolyUsersList"));
	}
	
}
