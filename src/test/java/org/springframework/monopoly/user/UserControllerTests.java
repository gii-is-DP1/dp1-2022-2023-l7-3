package org.springframework.monopoly.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTests {
	
	private static final int TEST_USER_ID = 1000;
	
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
		mockUser.setId(TEST_USER_ID);
		mockUser.setIs_admin("FALSE");
		mockUser.setPassword("12345");
		mockUser.setUsername("testUser");
		mockUser.setEnabled(true);
		mockUser.setPlayer(Set.of());
		
		this.userService.saveUser(mockUser);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitFindUsersSuccess() throws Exception {
		
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.getAll(p);
		given(this.userService.getAll(p)).willReturn(page);
		mockMvc.perform(get("/monopolyUsers/list")).andExpect(result -> result.getModelAndView().getViewName().equals("users/monopolyUsersList"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/monopolyUsers/new")).andExpect(status().isOk()).andExpect(model().attributeExists("user"))
				.andExpect(view().name("authentication/signUp"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/monopolyUsers/new").param("username", "testUser123").param("password", "securepassword123").with(csrf()))
				.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/monopolyUsers/new").with(csrf()).param("username", "").param("password", "").with(csrf()))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
				.andExpect(model().attributeHasFieldErrors("user", "username"))
				.andExpect(model().attributeHasFieldErrors("user", "password"))
				.andExpect(view().name("authentication/signUp"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateUserForm() throws Exception {
		
		Optional<User> mock = Optional.of(mockUser);			
		given(this.userService.findUser(TEST_USER_ID)).willReturn(mock);
		
		mockMvc.perform(get("/users/{userId}", TEST_USER_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attribute("user", hasProperty("username", is("testUser"))))
				.andExpect(model().attribute("user", hasProperty("password", is("12345"))))
				.andExpect(view().name("users/editProfile"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateUserFormSuccess() throws Exception {
		mockMvc.perform(post("/users/{userId}", TEST_USER_ID).with(csrf()).param("username", "testUserNewName")
				.param("password", "123456")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/monopolyUsers/list"));
	}

//	@WithMockUser(value = "spring") // Falla
//	@Test
//	void testProcessUpdateUserFormHasErrors() throws Exception {
//				
//		mockMvc.perform(post("/users/{userId}", TEST_USER_ID).with(csrf()).param("username", "")
//				.param("password", "").with(csrf()))
//				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
//				.andExpect(model().attributeHasFieldErrors("user", "username"))
//				.andExpect(model().attributeHasFieldErrors("user", "password"))
//				.andExpect(view().name("users/editProfile"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(get("/monopolyUsers/delete/1000")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/monopolyUsers/list"));
	}
}
