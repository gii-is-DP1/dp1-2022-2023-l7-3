package org.springframework.samples.petclinic.integration;

import static io.restassured.RestAssured.with;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Odd;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.http.ContentType;
import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Log
public class SportsEventAPIContractTest {
	@LocalServerPort
	private int port;
	@Test
	public void testDefaultEvent() {		
		when()
			.get("http://localhost:"+port+"/api/sportEvents/390")			
		.then()			
			.statusCode(200)
		  	.assertThat()
	      		.body("data.leagueId", equalTo(1))
	      	.and()
	      		.body("data.homeTeam",equalTo("Betis"));
		
	}	
	
	@Test
	public void testOddsPresent() {
		given()
			//.log().all()
		.when()
			.get("http://localhost:"+port+"/api/sportEvents/390")
		.then()
			.assertThat()
				.body("odds.price", 
					hasItems("1.25", "1.35"));
	}
	
	@Test
	public void whenRequestedPost_thenCreated() {
	    Odd odd=new Odd();
	    odd.setName("Empate");
	    odd.setPrice("0.3");
	    given()
	    	.request().contentType(ContentType.JSON)
	    			  .log().all()
			.response().log().all()		
	    .with()	    
	    	.body(odd)
	    .when()
	      .post("http://localhost:"+port+"/api/sportEvents/390/odds")
	    .then()
	      .statusCode(201)
	      .assertThat()
	      	.body("name",equalTo("Empate"));
	}
}
