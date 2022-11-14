package org.springframework.monopoly.game;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	private GameRepository gameRepository;
	private UserRepository userRepository;
	
	@Autowired
	public GameService(GameRepository gameRepository, UserRepository userRepository) {
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public Game saveGame(Game game) throws DataAccessException {
		return gameRepository.save(game);
	}
	
	public Optional<Game> findGame(Integer id) {
		return gameRepository.findById(id);
	}	
	
	public List<Player> getPlayersOrderedByTurn(Integer gameId) {
		return gameRepository.findPlayersOrderByTurn(gameId);
	}
	
	public Page<Game> getAll(Pageable pageable) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> u = userRepository.findByUsername(username);
		Page<Game> res = Page.empty();
		
		if (u.isPresent() && u.get().getIs_admin().equals("admin")) {
			res = gameRepository.findAll(pageable);
			
		} else if (u.isPresent() && !u.get().getIs_admin().equals("admin")) {
			res = gameRepository.findUserGames(u.get().getId(), pageable);
		}
		return res;
	}
		
}
