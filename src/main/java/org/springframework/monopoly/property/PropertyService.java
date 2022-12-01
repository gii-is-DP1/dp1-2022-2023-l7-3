package org.springframework.monopoly.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.turn.Action;
import org.springframework.monopoly.turn.Turn;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private StreetRepository streetRepository;
	private CompanyRepository companyRepository;
	private StationRepository stationRepository;
	
	@Autowired
	public PropertyService(StreetRepository streetRepository, CompanyRepository companyRepository, StationRepository stationRepository) {
		this.streetRepository = streetRepository;
		this.companyRepository = companyRepository;
		this.stationRepository = stationRepository;
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
		Object object =  getProperty(turn.getFinalTile(), turn.getGame().getId());
		if(object !=null) {
			if(streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId()) != null) {
				Street street = (Street) object;
				if( street.getOwner() == null) {
					if(turn.getPlayer().getMoney() >= street.getPrice()) {
						turn.setAction(Action.BUY);
					} else {
						turn.setAction(Action.AUCTION);
					}
				} else if(!turn.getPlayer().equals(street.getOwner())){
					if (turn.getPlayer().getMoney() >= getCalculateRentalPrice(turn)) {
						turn.setAction(Action.PAY);
					} else {
						turn.setAction(Action.MORTGAGE);
					}
				}else {
					turn.setAction(Action.NOTHING_HAPPENS);
				}
			} else {
				Property property = (Property) object;
				if( property.getOwner() == null) {
					if(turn.getPlayer().getMoney() >= property.getPrice()) {
						turn.setAction(Action.BUY);
					} else {
						turn.setAction(Action.AUCTION);
					}
				} else if(!turn.getPlayer().equals(property.getOwner())){
					if (turn.getPlayer().getMoney() >= getCalculateRentalPrice(turn)) {
						turn.setAction(Action.PAY);
					} else {
						turn.setAction(Action.MORTGAGE);
					}
				}else {
					turn.setAction(Action.NOTHING_HAPPENS);
				}
			}
		}
	}

	public void calculateActionProperty (Turn turn, Auction auction) {
		switch (turn.getAction()) {
			case BUY: 
				buyPropertyById(turn);
				break;
			case AUCTION: 
				setAuctionWinner(auction);
				break;
			case PAY: 
				payPropertyById(turn);
				break;
			case MORTGAGE: 
				mortgageProperty(turn);
				break;
			default:;
		}
	}

	public Integer getCalculateRentalPrice (Turn turn) {
		Object property = getProperty(turn.getFinalTile(), turn.getGame().getId());
		Integer n =0;
		if(streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payStreet(property, turn);
		}else if (stationRepository.findStationById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payStation(property, turn);
		} else if (companyRepository.findCompanyById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payCompany(property, turn); //hay que hacer una tirada y pasarla como parametro
		}
		return n;
	}

	public void buyPropertyById(Turn turn) {	
		Object property = getProperty(turn.getFinalTile(), turn.getGame().getId());
		if(streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			Street street = (Street)property;
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - street.getPrice());
			street.setOwner(turn.getPlayer());
		}else if (stationRepository.findStationById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			Station station = (Station)property;
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - station.getPrice());
			station.setOwner(turn.getPlayer());
		} else if (companyRepository.findCompanyById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			Company company = (Company)property;
			turn.getPlayer().setMoney(turn.getPlayer().getMoney() - company.getPrice());
			company.setOwner(turn.getPlayer());
		}
	}
	
	public void payPropertyById(Turn turn) {
		Integer n = 0;
		Object property = getProperty(turn.getFinalTile(), turn.getGame().getId());
		if(streetRepository.findStreetById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payStreet(property, turn);
			Street street = (Street)property;
			street.getOwner().setMoney(street.getOwner().getMoney() + n);	
		}else if (stationRepository.findStationById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payStation(property, turn);
			Station station = (Station) property;
			station.getOwner().setMoney(station.getOwner().getMoney() + n);
		} else if (companyRepository.findCompanyById(turn.getFinalTile(),turn.getGame().getId()) != null) {
			n = payCompany(property, turn); //hay que hacer una tirada y pasarla como parametro
			Company company = (Company) property;
			company.getOwner().setMoney(company.getOwner().getMoney() + n);
		}
		turn.getPlayer().setMoney(turn.getPlayer().getMoney() - n);
	}

	private Integer payStreet(Object property, Turn turn) {
		Street street = (Street)property;
		Boolean b = streetRepository.findStreetByColor(street.getColor().toString(), street.getGame().getId()).stream().allMatch(x -> x.getOwner() == street.getOwner());
		Integer n = 0;
		if(b) {
			if(street.getHaveHotel()) {
				n =street.getRentalHotel();
			}else {
				switch (street.getHouseNum()) {
					case 1: n = street.getRental1House();
						break;
					case 2: n = street.getRental2House();
						break;
					case 3: n = street.getRental3House();
						break;
					case 4: n =  street.getRental4House();
						break;
					default: n = street.getRentalPrice()*2;
						break;
				}
			}			
		} else {
			n = street.getRentalPrice();
		}	
		return n;	
	}

	private Integer payStation(Object property, Turn turn) {
		Station station = (Station) property;
		Integer n = (int) stationRepository.findByOwner(station.getOwner().getId(),station.getGame().getId()).stream().count();
		return station.getRentalPrice()*n;	
	}

	private Integer payCompany(Object property, Turn turn) {
		Company company = (Company) property;
		Integer n = 4; //llamar a roll aquí
		if (companyRepository.findByOwner(company.getOwner().getId(),company.getGame().getId()).stream().count() == 2.) n = 10;
		return company.getRentalPrice() * n;
	}
	
	//TODO
	private void mortgageProperty(Turn turn) {

	}
			
	public Auction auctionPropertyById(Auction auction) {
		Integer newBid = auction.getCurrentBid() + auction.getPlayerBid();
		List<Player> newRemaining = auction.getRemainingPlayers();
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
	
	private void setAuctionWinner(Auction auction) {
		Player auctionWinner = auction.getRemainingPlayers().get(0);
		auctionWinner.setMoney(auctionWinner.getMoney() - auction.getCurrentBid());
		auction.getProperty().setOwner(auctionWinner);	
	}	
}