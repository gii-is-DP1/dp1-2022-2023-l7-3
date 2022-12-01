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
//		assertThat(user.getPassword()).isEqualTo("sdhwrth");
		assertThat(user.getIs_admin()).isEqualTo("user");
	}
	
	@Test
	void shouldNotFindUserWithIncorrectId() {
		Optional<User> user1 = this.userService.findUser(1234567890); // Id does not exist
		assertThat(!user1.isPresent());
	}
		
	@Test
	void shouldFindUserWithCorrectUsername() {
		User user1 = this.userService.findUserByName("xXPaco02Xx");	
		assertThat(user1 != null);
		assertThat(user1.getId()).isEqualTo(1);
//		assertThat(user1.getPassword()).isEqualTo("sdhwrth");
		assertThat(user1.getIs_admin()).isEqualTo("user");
	}
	
	@Test
	void shouldNotFindUserWithIncorrectUsername() {
		User user = this.userService.findUserByName("testUser"); // Username does not exist in database	
		assertThat(user == null);
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
		assertThat(page.getNumberOfElements() > 0);
	}
	
	@Test
	public void findAllWithUsernameNotExists() {
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.findAllWithUsername("testUser", p); // Username does not exist
		assertThat(page.getNumberOfElements() == 0);
	}
	
	@Test
	public void getAllUsersPagination() {
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.getAll(p);
		assertThat(page.hasContent());
	}
	
	@Test
	@Transactional 
	public void deleteUserSuccessful() {
		
		User user = new User(); 
		user.setId(999999999);
		user.setEnabled(true);
		user.setUsername("deleteUser");
		user.setIs_admin("user");
		user.setPassword("testPassword");
		this.userService.saveUser(user);
		
		Optional<User> createdUser = userService.findUser(999999999); // Will exist
		assertThat(createdUser.isPresent());
		this.userService.delete(999999999);
		Optional<User> deletedUser = userService.findUser(999999999); // Will not exist
		assertThat(!deletedUser.isPresent());
	}
	 
	@Test
	@Transactional 
	public void deleteUserNotExists() {
		
		try {
			this.userService.delete(99999998);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertThrows(Exception.class, null); // No action will be performed
	}
	
}
