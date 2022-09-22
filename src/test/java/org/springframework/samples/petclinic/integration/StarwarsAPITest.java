package org.springframework.samples.petclinic.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;

@Log
public class StarwarsAPITest {
	@Test
	public void lukeSkywalkerIsPeople1() {
		when()
			.get("https://swapi.dev/api/people/1/")
		.then()
			.statusCode(200)
		.and()
			.assertThat()
				.body("name", equalTo("Luke Skywalker"))
				.body("hair_color", equalTo("blond"))
				.body("eye_color", equalTo("blue"))
				.body("gender",equalTo("male"))
				.body("films",hasSize(equalTo(4))) //Let´s forget episode 8...
			.and()
				.time(lessThan(20L), TimeUnit.SECONDS); 

	}
}
