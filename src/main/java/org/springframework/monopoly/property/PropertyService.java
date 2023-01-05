package org.springframework.monopoly.property;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.monopoly.exceptions.CantAfordMortgageException;
import org.springframework.monopoly.game.Game;
import org.springframework.monopoly.game.GameRepository;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerRepository;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.monopoly.util.RollGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

	private StreetRepository streetRepository;
	private CompanyRepository companyRepository;
	private StationRepository stationRepository;
	private PlayerRepository playerRepository;
	private GameRepository gameRepository;
	
	@Autowired
	public PropertyService( StreetRepository streetRepository, CompanyRepository companyRepository,
			StationRepository stationRepository , PlayerRepository playerRepository, GameRepository gameRepository) {
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
		this.playerRepository = playerRepository;
		this.gameRepository = gameRepository;
	}	
	
	@Transactional
	public void saveProperty(Object property) throws DataAccessException {
		Property p = (Property) property;
		Integer idProperty = p.getId();
		Integer idGame = p.getGame().getId();
		if (streetRepository.findStreetById(idProperty, idGame) !=null ) {
			streetRepository.save((Street) p);
		} else if(companyRepository.findCompanyById(idProperty, idGame) != null) {
			companyRepository.save((Company) p);
		} else if (stationRepository.findStationById(idProperty, idGame) != null) {
			stationRepository.save((Station) p);
		}
	}

	@Transactional()
	public List<Color> findPlayerColors(Integer gameId, Integer playerId) throws DataAccessException {
		return streetRepository.findPlayerColors(gameId, playerId);
	}

	@Transactional()
	public List<Street> findStreetByColor(Color color, Integer gameId) throws DataAccessException {
		return streetRepository.findStreetByColor(color, gameId);
	}

	public Object getProperty(Integer idProperty, Integer idGame) {
		if (streetRepository.findStreetById(idProperty, idGame) !=null ) {
			return streetRepository.findStreetById(idProperty, idGame);
		} else if(companyRepository.findCompanyById(idProperty, idGame) != null) {
			return companyRepository.findCompanyById(idProperty, idGame);
		} else if (stationRepository.findStationById(idProperty, idGame) != null) {
			return stationRepository.findStationById(idProperty, idGame);
		}else {
			return null;
		}
	}
	
	public void setActionProperty (Turn turn) {
		Property property = (Property) getProperty(turn.getFinalTile(), turn.getGame().getId());
		if(property !=null) {
			if( property.getOwner() == null) {
				if(turn.getPlayer().getMoney()>= property.getPrice()) {
					turn.setAction(Action.BUY);
				} else {
					turn.setAction(Action.AUCTION);
				}
			} else if(!turn.getPlayer().equals(property.getOwner())){
				if (turn.getPlayer().getMoney() >= getRentalPrice(property)) {
					turn.setAction(Action.PAY);
				} else {
					turn.setAction(Action.MORTGAGE);
				}
			}else {
				turn.setAction(Action.NOTHING_HAPPENS);
			}
		}
	}

	public void calculateActionProperty (Turn turn) {
		Property property = (Property) getProperty(turn.getFinalTile(), turn.getGame().getId());
		switch (turn.getAction()) {
			case BUY: 
				buyPropertyById(property, turn);
				break;
			case PAY: 
				payPropertyById(property, turn);
				break;
			default:;
		}
	}


	public void buyPropertyById(Property property, Turn turn) {	
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - property.getPrice());
			property.setOwner(turn.getPlayer());
			saveProperty(property);
			playerRepository.save(turn.getPlayer());

	}

	public Integer getRentalPrice(Property property) {
		Integer n =0;
		if(streetRepository.findStreetById(property.getId(),property.getGame().getId()) != null) {
			n = payStreet(property);	
		}else if (stationRepository.findStationById(property.getId(),property.getGame().getId()) != null) {
			n = payStation(property);
		} else if (companyRepository.findCompanyById(property.getId(),property.getGame().getId()) != null) {
			n = payCompany(property);
		}
		return n;
	}
	
	public void payPropertyById(Property property, Turn turn) {
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - getRentalPrice(property));
			property.getOwner().setMoney(property.getOwner().getMoney() + getRentalPrice(property));
			playerRepository.save(turn.getPlayer());
			playerRepository.save(property.getOwner());
	}

	private Integer payStreet(Property property) {
		Street street = (Street)property;
		Boolean b = streetRepository.findStreetByColor(street.getColor(), street.getGame().getId()).stream().allMatch(x -> x.getOwner() == street.getOwner());
		if(b) {
			if(street.getHaveHotel()) {
				return street.getRentalHotel();
			}else {
				switch (street.getHouseNum()) {
					case 1: return street.getRental1House();
					case 2: return street.getRental2House();
					case 3: return street.getRental3House();
					case 4: return street.getRental4House();
					default: return street.getRentalPrice()*2;
				}
			}			
		} else {
			return street.getRentalPrice();
		}		
	}

	private Integer payStation(Property property) {
		Station station = (Station) property;
		Integer n = (int) stationRepository.findByOwner(station.getOwner().getId(),station.getGame().getId()).stream().count();
		return station.getRentalPrice()*n;
	}

	private Integer payCompany(Property property) {
		Company company = (Company) property;
		Integer n = 4;
		if (companyRepository.findByOwner(company.getOwner().getId(),company.getGame().getId()).stream().count() == 2.) n = 10;
		return n * RollGenerator.getRoll().getFirst();
	}
	
	@Transactional
	public void mortgageProperty(Player player, Integer gameId, Integer propertyId) {
		Property property = (Property) getProperty(propertyId, gameId);
		
		if(playerRepository.findAllPropertyNamesByPlayer(gameId, player.getId()).contains(property.getName())) {
			
			if(property instanceof Street) {
				Street street = (Street) property;
				Integer buildingsMoney = 0;
				Game game = gameRepository.findById(gameId).get();
				
				if(street.getHaveHotel()) {
					buildingsMoney += street.getBuildingPrice() / 2;
					
					street.setHaveHotel(false);
					street.setHouseNum(4);
					game.setNumCasas(game.getNumCasas() + 1);
					gameRepository.save(game);
					
				} else if(street.getHouseNum() > 0) {
					buildingsMoney += street.getBuildingPrice() / 2;
					
					street.setHouseNum(street.getHouseNum() - 1);
					game.setNumCasas(game.getNumCasas() + 1);
					gameRepository.save(game);
					
				} else {
					street.setIsMortage(true);
					buildingsMoney += street.getMortagePrice();
				}
				
				player.setMoney(player.getMoney() + buildingsMoney);
				
				streetRepository.save(street);
				playerRepository.save(player);
				
			} else if(property instanceof Company) {
				Company company = (Company) property;
				
				player.setMoney(player.getMoney() + company.getMortagePrice());
				company.setIsMortage(true);
				
				companyRepository.save(company);
				playerRepository.save(player);
				
			} else if(property instanceof Station) {
				Station station = (Station) property;
				
				player.setMoney(player.getMoney() + station.getMortagePrice());
				station.setIsMortage(true);
				
				stationRepository.save(station);
				playerRepository.save(player);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public Boolean canPlayerPayProperty(Player player, Integer propertyId) {
		Property property = (Property) getProperty(propertyId, player.getGame().getId());
		return player.getMoney() >= getRentalPrice(property);
	}
	
	@Transactional(rollbackFor = CantAfordMortgageException.class)
	public void cancelMortgage(Player player, Integer gameId, Integer propertyId) throws CantAfordMortgageException {
		Property property = (Property) getProperty(propertyId, gameId);
		Integer mortgagePrice = (int) (property.getMortagePrice() * 1.1);
		
		if(player.getMoney() < mortgagePrice) {
			throw new CantAfordMortgageException("This player can't afford to cancel this mortgage");
		} else {
			property.setIsMortage(false);
			player.setMoney(player.getMoney() - mortgagePrice);
			
			playerRepository.save(player);
			saveProperty(property);
		}
	}
			

	public Auction auctionPropertyById(Auction auction) {
		
		Integer newBid = auction.getCurrentBid();
		Integer actualPlayerId = auction.getRemainingPlayers().get(auction.getPlayerIndex());
		Player player = playerRepository.findPlayerById(actualPlayerId, auction.getGameId());
		List<Integer> newRemaining = new ArrayList<>();
		newRemaining.addAll(auction.getRemainingPlayers());
		
		Integer newIndex = 0;
		
		if (newRemaining.size() > 1) {
			if (auction.getPlayerBid() == 0 || player.getMoney() < auction.getCurrentBid()) {
				 newRemaining.remove(auction.getRemainingPlayers().get(auction.getPlayerIndex()));
			} else {
				newBid = auction.getCurrentBid() + auction.getPlayerBid();
			}
			
			if (newRemaining.size() == auction.getRemainingPlayers().size()) {
				newIndex = (auction.getPlayerIndex() + 1)%(newRemaining.size());
			} else {
				newIndex = (auction.getPlayerIndex())%(newRemaining.size());
			}
		} 
		return new Auction(newIndex, newRemaining, newBid, 0, auction.getPropertyId(), auction.getGameId());
	}
	
	public void setAuctionWinner(Auction auction) {
		Player auctionWinner = playerRepository.findPlayerById(auction.getRemainingPlayers().get(0), auction.getGameId());
		auctionWinner.setMoney(auctionWinner.getMoney() - auction.getCurrentBid());
		Property property = (Property) getProperty(auction.getPropertyId(), auction.getGameId());
		property.setOwner(auctionWinner);
		
		playerRepository.save(auctionWinner);
		saveProperty(property);
	}
	
	public void buildProperty(Integer gameId, Integer playerId, StreetForm sf){
		Street street = (Street) getProperty(sf.getStreetId(), gameId);
		Player player = playerRepository.findPlayerById(playerId);
		if(player.getMoney()- getBuildingPrice(sf, street)>=0) player.setMoney(player.getMoney() - getBuildingPrice(sf, street));
		playerRepository.save(player);
	}

	private Integer getBuildingPrice (StreetForm sf, Street street) {
		Integer price = 0;

		if(sf.getHouse()!=null) {
			Boolean b= streetRepository.findStreetByColor(street.getColor(), street.getGame().getId()).stream()
			.allMatch(x -> Math.abs(sf.getHouse()-x.getHouseNum())<=1);
			if(true) {
				price +=(sf.getHouse()-street.getHouseNum())*street.getBuildingPrice();
				street.setHouseNum(sf.getHouse());
			}
		}
		
		if(sf.getHotel()!=null) {
			Boolean b2= streetRepository.findStreetByColor(street.getColor(), street.getGame().getId()).stream()
			.allMatch(x -> x.getHouseNum()>=4);
			if (b2) {
				price += street.getBuildingPrice();
				street.setHaveHotel(true);
			}	
		}
		saveProperty(street);
		return price;
	}
}
