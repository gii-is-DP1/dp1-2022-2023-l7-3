package org.springframework.monopoly.game;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public void saveGame(Game game) throws DataAccessException {
		gameRepository.save(game);
	}
	
	public Optional<Game> findGame(Integer id) {
		return gameRepository.findById(id);
	}	
}
