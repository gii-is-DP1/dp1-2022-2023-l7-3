package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerRepository;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private StreetRepository streetRepository;
	private CompanyRepository companyRepository;
	private StationRepository stationRepository;
	private PlayerRepository playerRepository;
	
	@Autowired
	public PropertyService(StreetRepository streetRepository, CompanyRepository companyRepository, StationRepository stationRepository , PlayerRepository playerRepository) {
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
		this.playerRepository = playerRepository;
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

	public void calculateActionProperty (Turn turn, Auction auction) {
		Property property = (Property) getProperty(turn.getFinalTile(), turn.getGame().getId());
		switch (turn.getAction()) {
			case BUY: 
				buyPropertyById(property, turn);
				break;
			case AUCTION: 
				setAuctionWinner(auction, turn);
				break;
			case PAY: 
				payPropertyById(property, turn);
				break;
			case MORTGAGE: 
				mortgageProperty(turn);
				break;
			default:;
		}
	}


	public void buyPropertyById(Property property, Turn turn) {	
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - property.getPrice());
			property.setOwner(turn.getPlayer());
	}

	public Integer getRentalPrice(Property property) {
		Integer n =0;
		if(streetRepository.findStreetById(property.getId(),property.getGame().getId()) != null) {
			n = payStreet(property);	
		}else if (stationRepository.findStationById(property.getId(),property.getGame().getId()) != null) {
			n = payStation(property);
		} else if (companyRepository.findCompanyById(property.getId(),property.getGame().getId()) != null) {
			n = payCompany(property); //hay que hacer una tirada y pasarla como parametro
		}
		return n;
	}
	
	public void payPropertyById(Property property, Turn turn) {
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - getRentalPrice(property));
			property.getOwner().setMoney(property.getOwner().getMoney() + getRentalPrice(property));	
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
		return company.getRentalPrice() * n; // falta multiplicarlo por la tirada específica para las compañias
	}
	

	private void mortgageProperty(Turn turn) {
		Integer buildingMoney = 0;
		if(streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			Street street = (Street)streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId());	
		if(playerRepository.findAllPropertyNamesByPlayer(turn.getGame().getId(), turn.getPlayer().getId()).contains(street.getName())) {
			if(street.getHaveHotel()) {
				buildingMoney += street.getBuildingPrice();
			}else if(street.getHouseNum() > 0) {
				buildingMoney += street.getBuildingPrice() * street.getHouseNum();
			}
			
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() + street.getMortagePrice() + buildingMoney);
			street.setIsMortage(true);
			street.setPrice(street.getMortagePrice());
		}
	}
	}
			

	public Auction auctionPropertyById(Auction auction) {
		Integer newBid = auction.getCurrentBid() + auction.getPlayerBid();
		List<Integer> newRemaining = auction.getRemainingPlayers();
		if (newRemaining.size() > 1) {
			if (auction.getPlayerBid() == 0) {
				newRemaining.remove(auction.getRemainingPlayers().get(auction.getPlayerIndex()));
			} 
			auction.setCurrentBid(newBid);
			auction.setPlayerBid(0);
			Integer newIndex = (auction.getPlayerIndex() + 1)%(newRemaining.size());
			auction.setPlayerIndex(newIndex);
			auction.setRemainingPlayers(newRemaining);
			return auction;
		} else {
			return null;
		}
	}
	
	private void setAuctionWinner(Auction auction, Turn turn) {
		Player auctionWinner = playerRepository.findPlayerById( auction.getRemainingPlayers().get(0));
		auctionWinner.setMoney(auctionWinner.getMoney() - auction.getCurrentBid());
		Property property = (Property) getProperty(auction.getPropertyId(), turn.getGame().getId());
		property.setOwner(auctionWinner);
		
	}
}
