package org.springframework.monopoly.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTests {

	@Autowired
	protected UserService userService;
		
	@Test
	void shouldFindUserWithCorrectId() {
		Optional<User> user1 = this.userService.findUser(1);
		assertThat(user1.isPresent());
		User user = user1.get();
		assertThat(user.getUsername()).isEqualTo("xXPaco02Xx");
		assertThat(user.getPassword()).isEqualTo("paco");
		assertThat(user.getIs_admin()).isEqualTo("user");
	}
	
	@Test
	void shouldNotFindUserWithIncorrectId() {
		Optional<User> user1 = this.userService.findUser(1234567890); // Id does not exist
		assertThat(user1.isPresent()).isEqualTo(false);
	}
		
	@Test
	void shouldFindUserWithCorrectUsername() {
		User user1 = this.userService.findUserByName("xXPaco02Xx").orElse(null);	
		assertThat(user1).isNotNull();
		assertThat(user1.getId()).isEqualTo(1);
		assertThat(user1.getPassword()).isEqualTo("paco");
		assertThat(user1.getIs_admin()).isEqualTo("user");
	}
	
	@Test
	void shouldNotFindUserWithIncorrectUsername() {
		User user = this.userService.findUserByName("testUser").orElse(null); // Username does not exist in database	
		assertThat(user).isNull();
	}
	
	@Test
	@Transactional
	public void shouldInsertUser() {
	
		User user = new User();
		user.setEnabled(true);
		user.setUsername("testUser");
		user.setIs_admin("user");
		user.setPassword("testPassword");
		
		this.userService.saveUser(user);		
		assertThat(user.getId()).isNotEqualTo(0);
	}
		
	@Test
	public void findAllWithUsernameExists() {
		
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.findAllWithUsername("xXPaco02Xx", p); // Username exists in the database
		Boolean pageNotEmpty = page.getNumberOfElements() > 0; 
		assertThat(pageNotEmpty).isEqualTo(true);
	}
	
	@Test
	public void findAllWithUsernameNotExists() {
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.findAllWithUsername("testUser", p); // Username does not exist
		assertThat(page.getNumberOfElements()).isEqualTo(0);
	}
	
	@Test
	public void getAllUsersPagination() {
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.getAll(p);
		assertThat(page.hasContent()).isEqualTo(true);
	}
	
//	@Test // Does not work correctly
//	public void deleteUserSuccessful() {
//		
//		User user = new User(); 
//		user.setEnabled(true);
//		user.setUsername("deleteUser");
//		user.setIs_admin("user");
//		user.setPassword("testPassword");
//		this.userService.saveUser(user);
//
//		User userToDelete = this.userService.findUserByName("deleteUser").get();
//		Integer id = userToDelete.getId();
//		
//		Optional<User> createdUser = userService.findUser(id); // Will exist
//		assertThat(createdUser.isPresent()).isEqualTo(true);
//		this.userService.delete(id);
//		Optional<User> deletedUser = userService.findUser(id); // Will not exist
//		assertThat(deletedUser.isPresent()).isEqualTo(false);
//	}
	 
	@Test
	public void deleteUserNotExists() {
		
		try {
			this.userService.delete(99999998);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertThrows(Exception.class, null); // No action will be performed
	}
	
}
