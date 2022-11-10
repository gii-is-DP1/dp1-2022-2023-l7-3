/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.monopoly.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO cambiar lo de petclinic

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {
	
	private UserRepository monopolyUserRepository;

	@Autowired
	public UserService(UserRepository monopolyUserRepository) {
		this.monopolyUserRepository = monopolyUserRepository;
	}
	
	@Transactional
	public List<User> findAll() {
		return monopolyUserRepository.findAll();
	}
	
	@Transactional
	public List<User> findAllWithUsername(String username) {
		return monopolyUserRepository.findAllWithUsername(username);
	}
	
	@Transactional
	public void saveUser(User monopolyUser) throws DataAccessException {
		monopolyUserRepository.save(monopolyUser);
	}
	
	@Transactional(readOnly = true)
	public Optional<User> findUser(Integer id) {
		return monopolyUserRepository.findById(id);
	}	
	
	@Transactional(readOnly = true)
	public User findUserByName(String name) {
		Optional<User> user = monopolyUserRepository.findByUsername(name);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

}
