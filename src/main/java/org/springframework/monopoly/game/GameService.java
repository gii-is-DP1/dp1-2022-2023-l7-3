package org.springframework.monopoly.game;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	private GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
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
}
