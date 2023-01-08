package org.springframework.monopoly.game;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.monopoly.card.Card;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.player.PieceColors;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Auction;
import org.springframework.monopoly.property.AuctionRepository;
import org.springframework.monopoly.property.Color;
import org.springframework.monopoly.property.Company;
import org.springframework.monopoly.property.CompanyService;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.property.Station;
import org.springframework.monopoly.property.StationService;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.property.StreetService;
import org.springframework.monopoly.tile.CommunityBox;
import org.springframework.monopoly.tile.CommunityBoxService;
import org.springframework.monopoly.tile.Generic;
import org.springframework.monopoly.tile.GenericService;
import org.springframework.monopoly.tile.Luck;
import org.springframework.monopoly.tile.LuckService;
import org.springframework.monopoly.tile.Taxes;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnRepository;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


@Service
public class GameService {
	
	private GameRepository gameRepository;
	
	private StreetService streetService;
	private CompanyService companyService;
	private StationService stationService;
	private UserService userService;
	private PlayerService playerService;
	private CommunityBoxService cbService;
	private LuckService luckService;
	private TaxesService taxService;
	private GenericService genericService;
	private TurnService turnService;
	private PropertyService propertyService;
	private CardService cardService;
	private AuctionRepository auctionRepository;
	
	@Autowired
	public GameService(GameRepository gameRepository, StreetService streetService,
			CompanyService companyService, StationService stationService, UserService userService,
			PlayerService playerService, CommunityBoxService cbService, LuckService luckService, TaxesService taxService,
			GenericService genericService, TurnService turnService, PropertyService propertyService, CardService cardService,
			AuctionRepository auctionRepository) {
		this.gameRepository = gameRepository;
		this.streetService = streetService;
		this.stationService = stationService;
		this.companyService = companyService;
		this.userService = userService;
		this.playerService = playerService;
		this.cbService = cbService;
		this.luckService = luckService;
		this.taxService = taxService;
		this.genericService = genericService;
		this.turnService = turnService;
		this.propertyService = propertyService;
		this.cardService = cardService;
		this.auctionRepository = auctionRepository;
	}
	
	@Transactional
	public Game saveGame(Game game) throws DataAccessException, InvalidNumberOfPLayersException {
		if(game.getPlayers().size() < 2 || game.getPlayers().size() > 6) {
			throw new InvalidNumberOfPLayersException();
		}
		return gameRepository.save(game);
	}
	
	@Transactional(readOnly = true)
	public Optional<Game> findGame(Integer id) {
		return gameRepository.findById(id);
	}	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Game setUpNewGame(GameForm createGameForm) throws InvalidNumberOfPLayersException {
		Game game = new Game();
		List<Integer> userIds = createGameForm.getUsers();
		List<User> users = new ArrayList<User>();
		for(Integer i:userIds) {
			User u = userService.findUser(i).orElse(null);
			if(u != null)
				users.add(u);
		}
		
		if(users.size() < 2 || users.size() > 6) {
			throw new InvalidNumberOfPLayersException();
		}
		
		return game;
	}
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Game setUpNewGamePlayers(List<Integer> userIds, Game game) throws DataAccessException {
		List<User> users = new ArrayList<User>();
		for(Integer i:userIds) {
			users.add(userService.findUser(i).get());
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
			
			p.setIs_bankrupcy(false);
			p.setGame(game);
			players.add(p);
		}
		
		game.setPlayers(new HashSet<Player>(players));
		return game;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Game setProperties(Game game) throws DataAccessException {
		Set<Company> blankCompanies = companyService.getBlankCompanies();
		Set<Company> savedCompanies = new HashSet<Company>();
		for(Company c:blankCompanies) {
			Company newCompany = new Company();
			BeanUtils.copyProperties(c, newCompany);
			newCompany.setGame(game);
			savedCompanies.add(newCompany);
		}
		
		Set<Street> blankStreets = streetService.getBlankStreets();
		Set<Street> savedStreets = new HashSet<Street>();
		for(Street s:blankStreets) {
			Street newStreet = new Street();
			BeanUtils.copyProperties(s, newStreet);
			newStreet.setGame(game);
			savedStreets.add(newStreet);
		}
		
		Set<Station> blankStations = stationService.getBlankStations();
		Set<Station> savedStations = new HashSet<Station>();
		for(Station s:blankStations) {
			Station newStation= new Station();
			BeanUtils.copyProperties(s, newStation);
			newStation.setGame(game);
			savedStations.add(newStation);
		}
		
		game.setCompanies(savedCompanies);
		game.setStreets(savedStreets);
		game.setStations(savedStations);
		
		return game;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void setTiles(Game game) {
		Set<CommunityBox> blankCommunityBoxes = cbService.getBlankCB();
		for(CommunityBox cb:blankCommunityBoxes) {
			CommunityBox newCB= new CommunityBox();
			BeanUtils.copyProperties(cb, newCB);
			newCB.setGame(game);
			cbService.save(newCB);
		}
		
		Set<Luck> blankLuck = luckService.getBlankLuck();
		for(Luck l:blankLuck) {
			Luck newLuck = new Luck();
			BeanUtils.copyProperties(l, newLuck);
			newLuck.setGame(game);
			luckService.save(newLuck);
			
		}
		
		Set<Generic> blankGenerics = genericService.getBlankGenerics();
		for(Generic g:blankGenerics) {
			Generic newGeneric = new Generic();
			BeanUtils.copyProperties(g, newGeneric);
			newGeneric.setGame(game);
			genericService.save(newGeneric);
		}
		
		Set<Taxes> blankTaxes = taxService.getBlankTaxes();
		for(Taxes t:blankTaxes) {
			Taxes newTax = new Taxes();
			BeanUtils.copyProperties(t, newTax);
			newTax.setGame(game);
			taxService.save(newTax);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Player> getPlayersOrderedByTurn(Integer gameId) {
		return gameRepository.findPlayersOrderByTurn(gameId);
	}
	
	@Transactional(readOnly = true)
	public Page<Game> getAll(Pageable pageable, String username) {
		
		Optional<User> u = userService.findUserByName(username);
		Page<Game> res = Page.empty();
		
		if (u.isPresent() && u.get().getIs_admin().equals("admin")) {
			res = gameRepository.findAll(pageable);
			
		} else if (u.isPresent() && !u.get().getIs_admin().equals("admin")) {
			res = gameRepository.findUserGames(u.get().getId(), pageable);
		}
		return res;
	}
	
	@Transactional(readOnly = true)
	public Integer findLastId() {
		return gameRepository.findLastId();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Model setupGameModel(Model model, Integer gameId, Authentication auth) throws Exception {
		Optional<Game> gameOptional = findGame(gameId);
		if(gameOptional.isEmpty()) {
			throw new Exception("Game doesn't exist");
		}  
		Game game = gameOptional.get();
		 
		User requestUser = userService.findUserByName(auth.getName()).orElse(null);
		if(requestUser == null) {
			throw new Exception("No such user found for that principal");
		}
		
		List<Player> players = new ArrayList<Player>(game.getPlayers());
		players = players.stream().filter(p -> !p.getIs_bankrupcy()).collect(Collectors.toList());
		Comparator<Player> c = Comparator.comparing(p -> p.getTurnOrder());
		Collections.sort(players, c);
		
		// If there is only 1 player left game has to be finished
		if(players.size() < 2) {
			
			if(game.getDuration() == null) {
				Player winnerPlayer = players.get(0);
				game.getPlayers().stream().filter(p -> p.equals(winnerPlayer)).forEach(p -> p.setIsWinner(true));
				
				Timestamp firstDate = game.getDate();
				Timestamp secondDate = Timestamp.valueOf(LocalDateTime.now());

			    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			    long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
			    game.setDuration(Long.valueOf(diff).intValue());
			    saveGame(game);
			}
		    
			model.addAttribute("player", players.get(0));
			model.addAttribute("gameId", gameId);
			model.addAttribute("finished", true);
			return model;
		}

		Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
		Turn turn = new Turn();
		turn.setGame(game);
		Player nextPlayer;
		Boolean isPlaying = false;
		
		// Calculating next turn result
		
		// Is there already any turn in this game?
		if(lastTurn != null) {
			
			// If we need a new turn and last was doubles, nextplayer is the same
			if(lastTurn.getIsFinished() && lastTurn.getIsDoubles()) {
				nextPlayer = lastTurn.getPlayer();
				
			/*
			 * If the turn is not finished we sort of don't care about the next player
			 * But if it was finished and last turn wasn't doubles, we need to set
			 * the next player
			 */
			} else {
				nextPlayer = players.get((players.indexOf(lastTurn.getPlayer()) + 1) % players.size());
			}
			
			/*
			 * If last turn is finished and the next player is the one who made the request 
			 * which is being attended right now, we need to get them a new turn
			 * 
			 */
			if(lastTurn.getIsFinished() && nextPlayer.getUser().equals(requestUser)) {
				addToGameVersion(gameId);
				
				turn.setPlayer(nextPlayer);
				turn.setInitial_tile(nextPlayer.getTile());
				turn.setTurnNumber(lastTurn.getTurnNumber() + 1);
				turnService.calculateTurn(turn);
				isPlaying = true;
				
			/*
			 * If the last turn is finished but the player who made the request is not the
			 * next one to play, we get them the last turn until the next player generates 
			 * the next one
			 */
			} else if(lastTurn.getIsFinished() && !nextPlayer.getUser().equals(requestUser)) {
				isPlaying = false;
				turn = lastTurn;
				
			/*
			 * If the last turn isn't finished, we need to deliver that turn so it can be
			 * finished. If the turn action is already evaluated and the player is correct,
			 * we prompt them for construction
			 */
			} else {
				isPlaying = lastTurn.getPlayer().getUser().equals(requestUser);
				turn = lastTurn;
				
				if(isPlaying && turn.getIsActionEvaluated()) {
					List<Color> colors= propertyService.findPlayerColors(gameId, turn.getPlayer().getId());
					
					List<Property> streets= new ArrayList<>();
					for (Color color: colors) {
						propertyService.findStreetByColor(color, gameId).forEach(x -> streets.add(x));;
					}
					
					model.addAttribute("streets", streets);
				}
			} 
		
		// We need to create the first turn of the game
		} else {
			nextPlayer = players.get(0);
			isPlaying = nextPlayer.getUser().equals(requestUser);
			turn.setPlayer(nextPlayer);
			turn.setInitial_tile(nextPlayer.getTile());
			turn.setTurnNumber(0);
			turnService.calculateTurn(turn);
			
		}
		
		/* 
		 * We need this attributes to be in the model, even if they are not modified
		 * by the next code here and therefore, not used.
		 */ 
		model.addAttribute("bankruptPlayer", null);
		model.addAttribute("hasToMortgage", false);
		
		 /*
		  * Adding needed things to the model based on what the turn action is
		  */
		model.addAttribute("property", propertyService.getProperty(turn.getFinalTile(), game.getId()));
		if(turn.getAction().equals(Action.PAY_TAX)) {
			model.addAttribute("taxes", taxService.findTaxesByGameId(gameId, turn.getFinalTile()).orElse(null));
			
		} else if(turn.getAction().equals(Action.DRAW_CARD)) {
			
			Card card = cardService.findCardById(turn.getActionCardId()).orElse(null);
			if(card != null) {
				model.addAttribute("drawCardSource", card.getBadgeImage());
			} 
			
		} else if(turn.getAction().equals(Action.AUCTION) && !turn.getIsAuctionOnGoing()) {
			turn.setIsAuctionOnGoing(true);
			turnService.saveTurn(turn);
			
			Auction auction = new Auction(0, players.stream().map(p -> p.getId()).collect(Collectors.toList()), 10, 0, turn.getFinalTile(), gameId);
			auction = auctionRepository.save(auction);
			model.addAttribute("auction", auction);
			
		} else if(turn.getAction().equals(Action.FREE)) {
			if(turn.getPlayer().getIsJailed()) {
				model.addAttribute("hasExitGate", turn.getPlayer().getHasExitGate());
			} else {
				turn.setAction(Action.NOTHING_HAPPENS);
			}
			
			
		} else if(turn.getAction().equals(Action.MORTGAGE)) {
			Boolean hasToMortgage = !propertyService.canPlayerPayProperty(turn.getPlayer(), turn.getFinalTile());
			model.addAttribute("hasToMortgage", hasToMortgage);
			model.addAttribute("needToPay", propertyService.getRentalPrice((Property) propertyService.getProperty(turn.getFinalTile(), gameId)));
			
			if(hasToMortgage) {
				testIfBankrupt(turn.getPlayer(), model);
			}
		}
		
		/*
		 * If the current player has negative money, because of taxes or cards,
		 * but still has properties owned, he needs to mortgage some.
		 * If he doesn't have properties, bankruptcy awaits.
		 */
		if(turn.getPlayer().getMoney() < 0) {
			model.addAttribute("hasToMortgage", true);
			model.addAttribute("needToPay", turn.getPlayer().getMoney() * -1);
			
			testIfBankrupt(turn.getPlayer(), model);
		}
		
		// To show the end turn button and popups if there is any
		model.addAttribute("isPlaying", isPlaying); 

		// Query the names of the properties of every player 
		List<List<String>> properties = new ArrayList<List<String>>();
		for(Player p:players) {
			properties.add(playerService.findPlayerPropertiesNames(p));
		} 

		model.addAttribute("Properties", properties);
		
		// Current player properties for mortgage
		if(isPlaying) {
			model.addAttribute("playerStreets", turn.getPlayer().getStreets());
			model.addAttribute("playerStations", turn.getPlayer().getStations());
			model.addAttribute("playerCompanies", turn.getPlayer().getCompanies());
		}
		    
		// Complete street colors of every player
 		List<List<Color>> colors = new ArrayList<List<Color>>();
		for(Player p:players) {
			colors.add(streetService.findPlayerColors(p));
		}
		
 		model.addAttribute("Colors", colors);
 		
 		model.addAttribute("Game", game);
		model.addAttribute("Turn", turn);
		model.addAttribute("Players", players);
		model.addAttribute("Version", game.getVersion());
		model.addAttribute("CurrentPlayer", turn.getPlayer().getUser().getUsername());
 		
		return model;
	}
	
	@Transactional
	public void testIfBankrupt(Player player, Model model) {
		Integer playerNumOfProperties = 0;
		playerNumOfProperties += player.getStreets().stream().filter(s -> !s.getIsMortage()).collect(Collectors.toList()).size();
		playerNumOfProperties += player.getStations().stream().filter(st -> !st.getIsMortage()).collect(Collectors.toList()).size(); 
		playerNumOfProperties += player.getCompanies().stream().filter(co -> !co.getIsMortage()).collect(Collectors.toList()).size(); 
		
		if(playerNumOfProperties == 0) {
			player.setIs_bankrupcy(true);
			
			player.getStreets().stream().forEach(s -> {
				s.setOwner(null);
				streetService.saveStreet(s);
			});
			
			player.getStations().stream().forEach(st -> {
				st.setOwner(null);
				stationService.saveStation(st);
			});
			
			player.getCompanies().stream().forEach(co -> {
				co.setOwner(null);
				companyService.saveCompany(co);
			});
			
			playerService.savePlayer(player);
			
			model.addAttribute("bankruptPlayer", player);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void evaluateTurn(Model model, Integer gameId, Authentication auth) throws Exception {
		Turn turn = turnService.findLastTurn(gameId).get();
		User requestUser = userService.findUserByName(auth.getName()).orElse(null);
		if(requestUser == null) {
			throw new Exception("No such user found for that principal");
		}
		Game game = findGame(gameId).get();
		
		if(turn.getPlayer().getUser().equals(requestUser) && !turn.getIsFinished()) {
			
			// If the player has not mortgaged a building yet, we cant finish the turn because he can't pay
			if(!(turn.getAction().equals(Action.MORTGAGE) && 
					!propertyService.canPlayerPayProperty(turn.getPlayer(), turn.getFinalTile()))) {
				if(!turn.getIsActionEvaluated()) {
					turnService.evaluateTurnAction(turn, false);
				}
				
				turn.setIsFinished(true);
				
				if(turn.getAction().equals(Action.DRAW_CARD)) {
					Card c = cardService.findCardById(turn.getActionCardId()).get();
					if(!(c.getAction().equals(Action.MOVE) || c.getAction().equals(Action.MOVETO))) {
						turn.getPlayer().setTile(turn.getFinalTile());
						playerService.savePlayer(turn.getPlayer());
					}
				} else {
					turn.getPlayer().setTile(turn.getFinalTile());
					playerService.savePlayer(turn.getPlayer());
				}
				
				game.setVersion(game.getVersion() + 1);
				saveGame(game);
				
				turnService.saveTurn(turn);
			}
		}
	}  
	
	@Transactional
	public void addToGameVersion(Integer gameId) throws DataAccessException, InvalidNumberOfPLayersException {
		Game game = findGame(gameId).orElse(null);
		
		if(game != null) {
			game.setVersion(game.getVersion() + 1);
			saveGame(game);
		}
	}
	
	@Transactional(readOnly = true)
	public Auction getLastAuction(Integer gameId) {
		List<Auction> auctions = gameRepository.findLastAuction(gameId);
		Comparator<Auction> c = Comparator.comparing(a -> a.getId());
		Collections.sort(auctions, c.reversed());
	
		return auctions.get(0);
	}

	@Transactional
	public Auction saveAuction(Auction auction) {
		return auctionRepository.save(auction);
	}
		
}
