package org.springframework.monopoly.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		assertThat(user.getPassword()).isEqualTo("sdhwrth");
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
		assertThat(user1.getPassword()).isEqualTo("sdhwrth");
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
	@Transactional 
	public void findAllWithUsernameExists() {
		
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.findAllWithUsername("xXPaco02Xx", p); // Username exists in the database
		assertThat(page.getSize() != 0);
	}
	
	@Test
	@Transactional 
	public void findAllWithUsernameNotExists() {
		Pageable p = PageRequest.of(0, 5);
		Page<User> page = userService.findAllWithUsername("testUser", p); // Username does not exist
		assertThat(page.getSize() == 0);
	}
	
	@Test
	@Transactional 
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
		userService.delete(999999999);
		Optional<User> deletedUser = userService.findUser(999999999); // Will not exist
		assertThat(!deletedUser.isPresent());
	}
	
	// TO DO 
//	@Test
//	@Transactional 
//	public void deleteUserNotExists() {
//		
//		Optional<User> deletedUser = userService.findUser(999999999); // Id does not exist
//		assertThat(!deletedUser.isPresent());
//		
//		userService.delete(999955555); 	
//	}
	
}
