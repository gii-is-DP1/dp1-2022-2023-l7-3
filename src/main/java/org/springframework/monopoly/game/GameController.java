package org.springframework.monopoly.game;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.monopoly.card.CardService;
import org.springframework.monopoly.exceptions.CantAfordMortgageException;
import org.springframework.monopoly.exceptions.InvalidNumberOfPLayersException;
import org.springframework.monopoly.exceptions.MortgageHousesNotUniform;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.monopoly.property.Auction;
import org.springframework.monopoly.property.Color;
import org.springframework.monopoly.property.Property;
import org.springframework.monopoly.property.PropertyService;
import org.springframework.monopoly.property.Street;
import org.springframework.monopoly.property.StreetForm;
import org.springframework.monopoly.tile.ExitGateForm;
import org.springframework.monopoly.tile.TaxesService;
import org.springframework.monopoly.tile.TileService;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.turn.TurnService;
import org.springframework.monopoly.user.User;
import org.springframework.monopoly.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GameController {
	
	private static final String VIEWS_NEW_GAME = "game/newGame";
	private static final String GAME_MAIN = "game/gameMain";
	private static final String BLANK_GAME = "game/blankGame"; //este es provisional para los tags
	public static final String GAMES_LISTING="game/GameList";
	private static final String END_GAME = "game/endGame";
	
	private GameService gameService;
	private PlayerService playerService;
	private UserService userService;
	private TurnService turnService;
	private PropertyService propertyService;
	private TileService tileService;
	
	@Autowired
	public GameController(GameService gameService, PlayerService playerService, UserService userService, TurnService turnService, 
			PropertyService propertyService, TaxesService taxesService, TileService tileService,
			CardService cardService) {
		this.gameService = gameService;
		this.playerService = playerService;
		this.userService = userService;
		this.turnService = turnService;
		this.propertyService = propertyService;
		this.tileService = tileService;
	}

	//PopUp Samples
	@GetMapping(value = "/blankGame")
	public String blankGame(Map<String, Object> model, Authentication authentication) {
		Integer idProperty = 12;
		Integer idGame = 2;
		Integer idPlayer = 5;
		Object property = propertyService.getProperty(idProperty, idGame);
		List<Integer> players = gameService.findGame(idGame).get().getPlayers().stream().map(p-> p.getId()).collect(Collectors.toList());
		Player player = playerService.findPlayerById(players.get(0));
		Auction auction = new Auction(0, players, 10, 0, idProperty, idGame); 
		List<Color> colors= propertyService.findPlayerColors(idGame, idPlayer);
		List<Property> streets= new ArrayList<>();
		for (Color c: colors) {
			propertyService.findStreetByColor(c, idGame).forEach(x -> streets.add(x));;
		}
		
		model.put("streets", streets );
		model.put("property", property );
		model.put("auction", auction);
		model.put("player", player);

		return BLANK_GAME;
	} 
	
	//Build sample
	@PostMapping(value="/blankGame/build")
	public String build(StreetForm streetForm,  Map<String,Object>model, Authentication authentication) {
		int gameId = 2;
		Integer idPlayer = 5;
		
		List<Color> colors= propertyService.findPlayerColors(gameId, idPlayer);
		List<Street> streets= new ArrayList<>();
		for (Color c: colors) {
			propertyService.findStreetByColor(c, gameId).forEach(x -> streets.add(x));;
		}
		model.put("streets", streets );
		Player player = playerService.findPlayerById(idPlayer);
		if(propertyService.getErrors(streetForm, streets, gameId)||propertyService.getBuildingPrice(streetForm,gameId)>player.getMoney()) {
			
			model.put("message",1);
			return BLANK_GAME;
		}
		
		
		propertyService.buildProperty(gameId, idPlayer, streetForm);
		return BLANK_GAME;
		
	}
	
	@PostMapping(value="/game/{gameId}/build")
	public String buildFinal(@PathVariable("gameId") int gameId, StreetForm streetForm, Model model, Authentication auth) throws Exception {
		Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
		if(lastTurn == null || !lastTurn.getIsActionEvaluated()) {
			return "redirect:/game/" + gameId;
		}
		 
		User requestUser = userService.findUserByName(auth.getName()).orElse(null);
		if(requestUser == null) {
			throw new Exception("No such user found for that principal");
		}
		
		Game game = gameService.findGame(gameId).orElse(null);
		if(game == null) {
			throw new Exception("Game does not exist");
		}
		
		Integer idPlayer = null;
		for(Player p:game.getPlayers()) {
			if(p.getUser().equals(requestUser)) {
				idPlayer = p.getId();
				break;
			}
		}

		List<Color> colors= propertyService.findPlayerColors(gameId, idPlayer);
		List<Street> streets= new ArrayList<>();
		for (Color c: colors) {
			propertyService.findStreetByColor(c, gameId).forEach(x -> streets.add(x));;
		}
		model.addAttribute("streets", streets );
		
		Player player = playerService.findPlayerById(idPlayer);
		if(propertyService.getErrors(streetForm, streets, gameId)||propertyService.getBuildingPrice(streetForm,gameId)>player.getMoney()) {
			model.addAttribute("message",1);
		} else {
			propertyService.buildProperty(gameId, idPlayer, streetForm);
		}
		
		model = gameService.setupGameModel(model, gameId, auth);
		
		return GAME_MAIN;
		
	}
	
	@PostMapping(value = "/blankGame")
	public String auction(Auction auction, Map<String, Object> model, Authentication authentication) {
		Integer idGame = 2;
		Object property = propertyService.getProperty(auction.getPropertyId(), idGame);
		Auction newAuction = propertyService.auctionPropertyById(auction);
		if(newAuction == null || newAuction.getRemainingPlayers().size() == 1) {
			//propertyService.setAuctionWinner(newAuction, turnService.findLastTurn(idGame).get());
			return GAMES_LISTING;
		}
		model.put("property", property );
		model.put("auction", newAuction);
		model.put("player", playerService.findPlayerById(newAuction.getRemainingPlayers().get(newAuction.getPlayerIndex())));
		
		return BLANK_GAME;
	}
	
	// Auction method with the real game mapping
	@GetMapping(value = "/game/{gameId}/auction")
	public String auctionGetFinal(@PathVariable("gameId") int gameId, Model model, Authentication authentication) throws Exception {
		Optional<Turn> turn = turnService.findLastTurn(gameId);
		User requestUser = userService.findUserByName(authentication.getName()).orElse(null);
		
		if(requestUser != null && turn.isPresent() && turn.get().getIsAuctionOnGoing()) {
			Auction oldAuction = gameService.getLastAuction(gameId);
			Object property = propertyService.getProperty(oldAuction.getPropertyId(), gameId);
			
			List<Player> playersOrdered = gameService.getPlayersOrderedByTurn(gameId);
			List<Player> playersOut = new ArrayList<Player>();
			playersOrdered.forEach(p -> {
				if(!oldAuction.getRemainingPlayers().contains(p.getId())) {
					playersOut.add(p);
				}
			});
			playersOrdered.removeAll(playersOut);
			oldAuction.setRemainingPlayers(playersOrdered.stream().map(p -> p.getId()).collect(Collectors.toList()));
			
			Player player = playerService.findPlayerById(oldAuction.getRemainingPlayers().get(oldAuction.getPlayerIndex()));
			model.addAttribute("property", property);
			model.addAttribute("auction", oldAuction);
			model.addAttribute("player", player);
			model.addAttribute("isActingOnAuction", player.getUser().equals(requestUser));
			
			model = gameService.setupGameModel(model, gameId, authentication);
			
			return GAME_MAIN;
		} else {
			return "redirect:/game/" + gameId;
		}
	}
	
	@PostMapping(value = "/game/{gameId}/auction")
	public String auctionPostFinal(@PathVariable("gameId") int gameId, Auction auction, Model model, Authentication authentication) throws Exception {
		Object property = propertyService.getProperty(auction.getPropertyId(), gameId);
		Auction newAuction = propertyService.auctionPropertyById(auction);
		newAuction = gameService.saveAuction(newAuction);
		
		if(newAuction == null || newAuction.getRemainingPlayers().size() == 1) {
			propertyService.setAuctionWinner(newAuction);
			
			model.addAttribute("property", property);
			model.addAttribute("auction", newAuction);
			model.addAttribute("player", playerService.findPlayerById(newAuction.getRemainingPlayers().get(newAuction.getPlayerIndex())));
			
			model = gameService.setupGameModel(model, gameId, authentication);
			
			Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
			
			if(lastTurn != null) {
				lastTurn.setIsAuctionOnGoing(false);
				turnService.evaluateTurnAction(lastTurn, false);
			}  
			
			Game game = gameService.findGame(gameId).get();
			game.setVersion(game.getVersion()+1);
			gameService.saveGame(game);
			
			return GAME_MAIN;
		} else {
			return "redirect:/game/" + gameId + "/auction";
		}
		
	}
	
	@PostMapping(value= "/game/{gameId}/mortgage")
	public String mortgageProperty(@PathVariable("gameId") int gameId, @ModelAttribute("MortgagePropertyForm") MortgagePropertyForm propertyForm, BindingResult bindingResult, Model model, Authentication authentication) throws Exception {
		Integer propertyId = propertyForm.getPropertyId();
		
		Optional<Turn> optionalTurn = turnService.findLastTurn(gameId);
		
		User requestUser = userService.findUserByName(authentication.getName()).orElse(null);
		
		if(propertyId < 0) {
			bindingResult.rejectValue("propertyId", "NoPropertySelected", "You didn't select any property");
			
			gameService.setupGameModel(model, gameId, authentication);
			return GAME_MAIN;
		}
		
		if(optionalTurn.isPresent()) {
			Turn turn = optionalTurn.get();
			
			if(!turn.getIsFinished() && turn.getPlayer().getUser().equals(requestUser)) {
				
				Property property = (Property) propertyService.getProperty(propertyId, gameId);
				
				if(!property.getOwner().equals(turn.getPlayer())) {
					bindingResult.rejectValue("propertyId", "PropertyNotOwned", "You don't own that property");
				} else if(property.getIsMortage()) {
					bindingResult.rejectValue("propertyId", "PropertyAlreadyMortgaged", "This property is already mortgaged!");
				} else {
					try {
						propertyService.mortgageProperty(turn.getPlayer(), gameId, propertyId);
						gameService.addToGameVersion(gameId);
					} catch (MortgageHousesNotUniform e) {
						bindingResult.rejectValue("propertyId", "HouseMortgageNotUniform", "You have to sell houses uniformly in each color group");
					}
				}
				
				gameService.setupGameModel(model, gameId, authentication);
				return GAME_MAIN;
			}
		}
		
		return "redirect:/game/" + gameId;
	}
	
	@PostMapping(value= "/game/{gameId}/cancelMortgage")
	public String cancelMortgageProperty(@PathVariable("gameId") int gameId, @ModelAttribute("MortgagePropertyForm") MortgagePropertyForm propertyForm, BindingResult bindingResult, Model model, Authentication authentication) throws Exception {
		Integer propertyId = propertyForm.getPropertyId();
		
		Optional<Turn> optionalTurn = turnService.findLastTurn(gameId);
		
		User requestUser = userService.findUserByName(authentication.getName()).orElse(null);
		
		if(propertyId < 0) {
			bindingResult.rejectValue("propertyId", "NoPropertySelected", "You didn't select any property");
			
			gameService.setupGameModel(model, gameId, authentication);
			return GAME_MAIN;
		}
		
		if(optionalTurn.isPresent()) {
			Turn turn = optionalTurn.get();
			
			if(!turn.getIsFinished() && turn.getPlayer().getUser().equals(requestUser)) {
				
				Property property = (Property) propertyService.getProperty(propertyId, gameId);
				
				if(!property.getOwner().equals(turn.getPlayer())) {
					bindingResult.rejectValue("propertyId", "PropertyNotOwned", "You don't own that property!");
				} else if(!property.getIsMortage()) {
					bindingResult.rejectValue("propertyId", "PropertyNotMortgaged", "That property isn't mortgaged!");
				} else {
					try {
						propertyService.cancelMortgage(turn.getPlayer(), gameId, propertyId);
						gameService.addToGameVersion(gameId);
					} catch(CantAfordMortgageException e) {
						bindingResult.rejectValue("propertyId", "CantAfordCancelMortgage", "You can't afford to cancel that mortgage!");
					}
				}
				
				gameService.setupGameModel(model, gameId, authentication);
				return GAME_MAIN;
			}
		}
		
		return "redirect:/game/" + gameId;
	}
	
	@PostMapping(value = "/exitGate")
	public String getOutOfJail(ExitGateForm exitGateForm, Map<String, Object> model, Authentication authentication) {
		
		Integer option = exitGateForm.getOption();
		tileService.calculateActionTile(null, option);
		
		return GAMES_LISTING;
	}
	
	@PostMapping(value = "/game/{gameId}/exitGate")
	public String getOutOfJailFinal(@PathVariable("gameId") int gameId, ExitGateForm exitGateForm, Map<String, Object> model, Authentication authentication) {
		Optional<Turn> optionalTurn = turnService.findLastTurn(gameId);
		User requestUser = userService.findUserByName(authentication.getName()).orElse(null);
		
		if(optionalTurn.isPresent()) {
			Turn turn = optionalTurn.get();
			
			if(turn.getPlayer().getUser().equals(requestUser)) {
				turn.setIsActionEvaluated(true);
				Integer option = exitGateForm.getOption();
				tileService.calculateActionTile(turn, option);
			}
		}
		
		
		
		return "redirect:/game/" + gameId;
	}

	
	@GetMapping(value = "/newGame")
	public String newGame(Model model, Authentication authentication) throws Exception {
		List<User> users = userService.findAll();
		
		User creatorUser = userService.findUserByName(authentication.getName()).orElse(null);
		if(creatorUser == null) {
			throw new Exception("No such user found for that principal");
		}
		users.remove(creatorUser);
		
		List<User> players = new ArrayList<User>();
		players.add(creatorUser);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/add/{userId}")
	public String postCreatingGameAddPlayer(@PathVariable("userId") int userId, GameForm gameForm, Model model) {
		User addedUser = userService.findUser(userId).get();
		
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.add(addedUser);
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame/creating/remove/{playerNum}")
	public String creatingGameRemovePlayer(@PathVariable("playerNum") int playerNum, GameForm gameForm, Model model) {
		List<Integer> playerIds = gameForm.getUsers();
		List<User> players = new ArrayList<User>();
		for(Integer i:playerIds) {
			players.add(userService.findUser(i).get());
		}
		
		players.remove(playerNum);
		
		List<User> users = userService.findAll();
		users.removeAll(players);
		
		model.addAttribute("users", users);
		model.addAttribute("players", players);
		return VIEWS_NEW_GAME;
	}
	
	@PostMapping(value = "/newGame")
	public String processGameForm(GameForm gameForm, Model model, Authentication authentication) throws Exception {
		Game savedGame; 
		if(gameForm.getUsers().size() < 2 || gameForm.getUsers().size() > 6) {
			// Doesn't work with a Binding result
//			result.rejectValue("", "InvalidNumberOfPlayers", "The number of players must be between 2 and 6");
			
			List<User> users = userService.findAll();
			
			User creatorUser = userService.findUserByName(authentication.getName()).orElse(null);
			if(creatorUser == null) {
				throw new Exception("No such user found for that principal");
			}
			users.remove(creatorUser);
			
			List<User> players = new ArrayList<User>();
			players.add(creatorUser);
			
			model.addAttribute("users", users);
			model.addAttribute("players", players);
			
			// Workarround to errors
			model.addAttribute("error", "The number of players must be between 2 and 6");
			 
			return VIEWS_NEW_GAME;
		} else { 
			try {
				savedGame = gameService.setUpNewGame(gameForm);
				savedGame = gameService.setUpNewGamePlayers(gameForm.getUsers(), savedGame);
				savedGame = gameService.setProperties(savedGame);
				savedGame = gameService.saveGame(savedGame);
				gameService.setTiles(savedGame); // Caution: Saves new tiles directly to database
				
			} catch (InvalidNumberOfPLayersException e) {
				// Realisticly, should not happen ever
				e.printStackTrace();
				return "/welcome";
			}
			return "redirect:/game/" + savedGame.getId();
		}
	} 
	
	@GetMapping(value = "/game/{gameId}")
	public String loadGame(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		model = gameService.setupGameModel(model, gameId, auth);
		 
		if(model.containsAttribute("finished")) {
			return END_GAME;
		}
		
		Turn turn = (Turn) model.getAttribute("Turn");
		if(turn != null && turn.getAction().equals(Action.AUCTION)) {
			return "redirect:/game/" + gameId + "/auction";
		}
		
		return GAME_MAIN;
	}  
	
	@PostMapping(value = "/game/{gameId}/tileAction")
	public String evalTurnAction(@PathVariable("gameId") int gameId, Boolean decisionResult, Authentication auth, Model model) throws Exception {
		Turn lastTurn = turnService.findLastTurn(gameId).orElse(null);
		
		if(lastTurn != null) {
			// If the player has not mortgaged a building yet, we cant finish the turn because he can't pay
			if(lastTurn.getAction().equals(Action.MORTGAGE) && 
					!propertyService.canPlayerPayProperty(lastTurn.getPlayer(), lastTurn.getFinalTile())) {
				return "redirect:/game/" + gameId;
			}
			
			if(lastTurn.getAction().equals(Action.BUY) && !decisionResult) {
				lastTurn.setAction(Action.AUCTION);
				turnService.saveTurn(lastTurn);
			} else { 
				turnService.evaluateTurnAction(lastTurn, decisionResult);
			}
			
			Game game = gameService.findGame(gameId).get();
			game.setVersion(game.getVersion()+1);
			gameService.saveGame(game);
		}  
		
		return "redirect:/game/" + gameId;
	}  
	
	@GetMapping(value = "/game/{gameId}/endTurn")
	public String endTurn(@PathVariable("gameId") int gameId, Authentication auth, Model model) throws Exception {
		gameService.evaluateTurn(model, gameId, auth);
		return "redirect:/game/" + gameId;
	}
		
    @GetMapping("/games/list")
    public ModelAndView showGamesListing(@RequestParam Map<String, Object> params, Principal auth) {
    	int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())) : 0;
		Page<Game> pageGame = gameService.getAll(PageRequest.of(page, 5), auth.getName());

        ModelAndView result=new ModelAndView(GAMES_LISTING);
        result.addObject("games", pageGame.getContent());
        
        int totalPages = pageGame.getTotalPages();
        
        if(totalPages > 0) {
			List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());
			result.addObject("pages", pages);
		}
        
        return result;
    }
	   	
}
