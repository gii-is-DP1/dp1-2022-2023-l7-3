package org.springframework.monopoly.turn;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TurnServiceTests {
	
	@Autowired
	TurnService turnService;
	
	@Test
	void shouldFindLastTurn() {
		Turn lastTurn = turnService.findLastTurn(0);
		assertThat(lastTurn.getTurnNumber()).isEqualTo(2);
	}
	
}
