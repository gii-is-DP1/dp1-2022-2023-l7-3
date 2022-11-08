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
package org.springframework.monopoly.player;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.monopolyUser.MonopolyUserService;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.PropertyRepository;
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
public class PlayerService {

	private PlayerRepository playerRepository;	
	
	@Autowired
	private MonopolyUserService monopolyUserService;
	
	private PropertyRepository propertyRepository;
	
	// @Autowired
	// private AuthoritiesService authoritiesService;

	@Autowired
	public PlayerService(PlayerRepository playerRepository, PropertyRepository propertyRepository) {
		this.playerRepository = playerRepository;
		this.propertyRepository = propertyRepository;
	}

	@Transactional(readOnly = true)
	public Player findPlayerById(int id) throws DataAccessException {
		Optional<Player> result = playerRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		playerRepository.save(player);		
		monopolyUserService.saveUser(player.getMonopolyUser());
		
		// authoritiesService.saveAuthorities(owner.getUser().getUsername(), "owner");
	}		

	public void getPropertyById(Integer idProperty, Integer idPlayer) {
		Optional<Property> property = propertyRepository.findById(idProperty);
		Player player = playerRepository.findPlayerById(idPlayer);
		if(player.getMoney()>= property.get().getPrice()) {
			property.get().setPlayer(player);
		}
		
		
	}
}
