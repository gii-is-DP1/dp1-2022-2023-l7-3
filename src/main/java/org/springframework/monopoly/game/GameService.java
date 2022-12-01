package org.springframework.monopoly.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.PieceColors;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.CompanyService;
import org.springframework.monopoly.property.StationService;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserRepository;
import org.springframework.monopoly.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	private GameRepository gameRepository;
	private UserRepository userRepository;
	
	private StreetService streetService;
	private CompanyService companyService;
	private StationService stationService;
	private UserService userService;
	private PlayerService playerService;
	
	@Autowired
	public GameService(GameRepository gameRepository, UserRepository userRepository, StreetService streetService,
			CompanyService companyService, StationService stationService, UserService userService,
			PlayerService playerService) {
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.streetService = streetService;
		this.stationService = stationService;
		this.companyService = companyService;
		this.userService = userService;
		this.playerService = playerService;
	}
	
	@Transactional
	public Game saveGame(Game game) throws DataAccessException, InvalidNumberOfPLayersException {
		if(game.getPlayers().size() < 2 || game.getPlayers().size() > 6) {
			throw new InvalidNumberOfPLayersException();
		}
		return gameRepository.save(game);
	}
	
	public Optional<Game> findGame(Integer id) {
		return gameRepository.findById(id);
	}	
	
	public Game setUpNewGame(GameForm createGameForm) throws InvalidNumberOfPLayersException {
		Game game = new Game();
		List<Integer> userIds = createGameForm.getUsers();
		List<User> users = new ArrayList<User>();
		for(Integer i:userIds) {
			users.add(userService.findUser(i).get());
		}
		
		if(users.size() < 2 || users.size() > 6) {
			throw new InvalidNumberOfPLayersException();
		}
		
		List<PieceColors> colors = playerService.getAllPieceTypes();
		Collections.shuffle(colors);
		List<Integer> turns = Stream.iterate(0, i-> i<users.size(), i -> ++i).collect(Collectors.toList());
		Collections.shuffle(turns);
		
		List<Player> players = new ArrayList<Player>();
		int i = 0;
		for(User u : users) {
			Player p = new Player();
			p.setUser(u);
			
			p.setIsJailed(false);
			p.setTile(0);
			p.setMoney(1500);
			p.setPiece(colors.get(i));
			p.setHasExitGate(false);
			p.setTurnOrder(turns.get(i++));
			p.setGame(game);
			p.setIs_bankrupcy(false);
			
			playerService.savePlayer(p);
			players.add(p);
		}
		
		game.setPlayers(new HashSet<Player>(players));
		return game;
	}
	
	public void setProperties(Game game) {
//		Set<Company> blankCompanies = companyService.getBlankCompanies();
//		for(Company c:blankCompanies) {
//			Company newC = new Company();
//			newC.setId(c.getId());
//			newC.setName(c.getName());
//			newC.setPrice(c.getPrice());
//			newC.setRentalPrice(c.getRentalPrice());
//			newC.setMortagePrice(c.getMortagePrice());
//			newC.setIsMortage(c.getIsMortage());
//			newC.setBadgeImage(c.getBadgeImage());
//			newC.setGame(game);
//			companyService.customSaveCompany(newC);
//		}
		
//		game.setCompanies(blankCompanies);
//		try {
//			saveGame(game);
//		} catch (DataAccessException | InvalidNumberOfPLayersException e) {
//			e.printStackTrace();
//		}
	}
	
	public List<Player> getPlayersOrderedByTurn(Integer gameId) {
		return gameRepository.findPlayersOrderByTurn(gameId);
	}
	
	public Page<Game> getAll(Pageable pageable, String username) {
		
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
