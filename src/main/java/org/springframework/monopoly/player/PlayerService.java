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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.user.UserService;
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
	private UserService monopolyUserService;
	
	
	
	// @Autowired
	// private AuthoritiesService authoritiesService;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
		
	}

	@Transactional(readOnly = true)
	public List<Player> findAll() {
		return new ArrayList<Player>(playerRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public Player findPlayerById(int id) throws DataAccessException {
		Optional<Player> result = playerRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}
	
	@Transactional(readOnly = true)
	public Player findPlayerByUserId(int id) throws DataAccessException {
		Optional<Player> result = playerRepository.findPlayerByUser(id);
		return result.isPresent() ? result.get() : null;
	}

	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		playerRepository.save(player);		
		monopolyUserService.saveUser(player.getUser());
		
		// authoritiesService.saveAuthorities(owner.getUser().getUsername(), "owner");
	}	
	
	@Transactional
	public List<PieceColors> getAllPieceTypes() {
		PieceColors[] pieceColors = PieceColors.values();
		List<PieceColors> colors = Arrays.asList(pieceColors);
		return colors;
	}
	
	
	
}
