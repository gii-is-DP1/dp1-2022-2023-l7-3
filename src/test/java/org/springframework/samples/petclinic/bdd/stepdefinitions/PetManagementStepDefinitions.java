package org.springframework.samples.petclinic.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;

@Log
public class PetManagementStepDefinitions extends AbstractStep {
	
	@LocalServerPort
	private int port;
	
	private int mascotasAlInicio;
	private String nombreMascota="Bufi";
	
	@Given("I am logged in the system as {string}")
	public void i_am_logged_in_the_system_as_owner(String username) {
	    LoginStepDefinitions.loginAs(username, port, getDriver());
	}

	@When("I add a new pet")
	public void i_add_a_new_pet() {		 
		    getDriver().get("http://localhost:"+port+"/");
		    getDriver().findElement(By.xpath("//div[@id='main-navbar']/ul/li[2]/a/span[2]")).click();
		    getDriver().findElement(By.xpath("//button[@type='submit']")).click();
		    getDriver().findElement(By.linkText("George Franklin")).click();
		    mascotasAlInicio=contarMascotas();
		    getDriver().findElement(By.linkText("Add New Pet")).click();
		    getDriver().findElement(By.id("name")).click();
		    getDriver().findElement(By.id("name")).clear();
		    getDriver().findElement(By.id("name")).sendKeys(nombreMascota);		    
		    getDriver().findElement(By.id("birthDate")).click();
		    getDriver().findElement(By.id("birthDate")).clear();
		    getDriver().findElement(By.id("birthDate")).sendKeys("2012/03/01");
		    Select select=new Select(getDriver().findElement(By.id("type")));
		    select.selectByVisibleText("dog");
		    getDriver().findElement(By.xpath("//option[@value='dog']")).click();
		    getDriver().findElement(By.xpath("//button[@type='submit']")).click();		  
	}

	private int contarMascotas() {
		WebElement tablaMascotas=getDriver().findElement(By.xpath("//table[2]"));
	    List<WebElement> filasDeTablaMascotas=tablaMascotas.findElements(By.tagName("tr"));
	    return filasDeTablaMascotas.size();
	}

	@Then("the new pet appears in my profile page")
	public void the_new_pet_appears_in_my_profile_page() {
	    // Hay exactamente una mascota más
		assertTrue(contarMascotas() > mascotasAlInicio );
	    // El nombre de la nueva mascota aparece en la tabla de mascotas 
		assertTrue(getDriver().findElement(By.xpath("//table[2]")).getText().contains(nombreMascota));
		stopDriver();
	}
}
