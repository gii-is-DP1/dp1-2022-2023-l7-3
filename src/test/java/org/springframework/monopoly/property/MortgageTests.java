package org.springframework.monopoly.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.monopoly.exceptions.MortgageHousesNotUniform;
import org.springframework.monopoly.player.Player;
import org.springframework.monopoly.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MortgageTests {
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private PlayerService playerService;
	
	/*
	 * Game id: 2
	 * Player id: 5
	 * Streets suitable for mortgage: 1, 3 -> Color: Brown
	 * Not suitable streets: 16, 18, 19
	 * Stations: 15, 5
	 * Not suitable: 25, 35
	 * Companies: 28, 12 (Ownerless)
	 */
	int gameId = 2;
	int playerId = 5;
	Player player;
	Integer initialMoney;
	
	int suitableStreetId1 = 1; // Has 2 houses
	int suitableStreetId2 = 3; // Has 1 house
	int suitableStreetId3 = 31; // Has no houses
	int suitableStreetId4 = 16; // Has hotel, needs house num fixing along with 18
	int notSuitableStreetId = 6; // Not owned
	
	int stationId = 15;
	
	int companyId = 12; // Not owned, will setup so it is
	
	@BeforeEach
	void setup() {
		player = playerService.findPlayerById(playerId);
		initialMoney = player.getMoney();
		 
		Street street1 = (Street) propertyService.getProperty(suitableStreetId4, gameId);
		Street street2 = (Street) propertyService.getProperty(18, gameId);
		street1.setHouseNum(4);
		street2.setHouseNum(4);
		propertyService.saveProperty(street1);
		propertyService.saveProperty(street2);
		
		Company company = (Company) propertyService.getProperty(companyId, gameId);
		company.setOwner(player);
		propertyService.saveProperty(company);
		
	}
	
	@Test
	void shouldMortgageStreetWithHouses() throws Exception {
		propertyService.mortgageProperty(player, gameId, suitableStreetId1);
		
		Street street = (Street) propertyService.getProperty(suitableStreetId1, gameId);
		assertThat(street.getHouseNum() == 1).isTrue();
		assertThat(street.getIsMortage()).isFalse();
		assertThat(initialMoney + street.getBuildingPrice()/2 == player.getMoney()).isTrue();
	}
	
	@Test
	void shouldFailHouseSellingNotUniform() throws MortgageHousesNotUniform {
		assertThrows(MortgageHousesNotUniform.class,
					 () -> propertyService.mortgageProperty(player, gameId, suitableStreetId2));
	}
	
	@Test
	void shouldMortgageStreetWithoutHouses() throws Exception {
		propertyService.mortgageProperty(player, gameId, suitableStreetId3);
		
		Street street = (Street) propertyService.getProperty(suitableStreetId3, gameId);
		assertThat(street.getHouseNum() == 0).isTrue();
		assertThat(street.getIsMortage()).isTrue();
		assertThat(initialMoney + street.getMortagePrice() == player.getMoney()).isTrue();
	}
	
	@Test
	void shouldMortgageStreetWithHotel() throws Exception {
		propertyService.mortgageProperty(player, gameId, suitableStreetId4);
		
		Street street = (Street) propertyService.getProperty(suitableStreetId4, gameId);
		assertThat(street.getHaveHotel()).isFalse();
		assertThat(street.getIsMortage()).isFalse();
		assertThat(initialMoney + street.getBuildingPrice()/2 == player.getMoney()).isTrue();
	}
	
	@Test  
	void shouldDoNothingPropertyNotOwned() throws MortgageHousesNotUniform {
		propertyService.mortgageProperty(player, gameId, notSuitableStreetId);
		
		Street street = (Street) propertyService.getProperty(notSuitableStreetId, gameId);
		assertThat(street.getIsMortage()).isFalse();
	}
	
	@Test
	void shouldMortgageStation() throws MortgageHousesNotUniform {
		propertyService.mortgageProperty(player, gameId, stationId);
		
		Station station = (Station) propertyService.getProperty(stationId, gameId);
		assertThat(station.getIsMortage()).isTrue();
		assertThat(initialMoney + station.getMortagePrice() == player.getMoney()).isTrue();
	}
	
	@Test
	void shouldMortgageCompany() throws MortgageHousesNotUniform {
		propertyService.mortgageProperty(player, gameId, companyId);
		
		Company company = (Company) propertyService.getProperty(companyId, gameId);
		assertThat(company.getIsMortage()).isTrue();
		assertThat(initialMoney + company.getMortagePrice() == player.getMoney()).isTrue();
	}
	
}
