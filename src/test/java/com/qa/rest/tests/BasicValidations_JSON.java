package com.qa.rest.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

/* 1) Test status code
 * 2) Log Response
 * 3) Verifying Single content in response body
 * 4) verifying Multiple contents in response body
 * 5) Setting parameters and headers 
 * 
 */
public class BasicValidations_JSON {

//	 1) Test status code
	@Test(priority=1)
	 public void testStatusCode() {
		given()  //no prerequisite, so given() could be removed, then "when" w/o dot!
		
		.when()
			.get("https://jsonplaceholder.typicode.com/posts/2")
		
		.then()
			.statusCode(200)
			.log().all();
	//can write in a single line
		//given().when().get("https://jsonplaceholder.typicode.com/posts/2").then().statusCode(200);
	}
	@Test(priority=2)
	public void testLogging() {
		given()
		.when()
			.get("https://restcountries.eu/rest/v2/name/poland") //no id //.get("https://reqres.in/api/users/12")
		.then()
			.statusCode(200)
			.log().all();
	}
	//testing single content in the response
	@Test(priority=3)
	public void testSingleContent() {
		given()
		.when()
			.get("https://restcountries.eu/rest/v2/name/poland") //no id //.get("https://reqres.in/api/users/12")
		.then()
			.statusCode(200)
			.body("[0].currencies[0].code", equalTo("PLN"));  //to get JSONPath for an element use chrome plugin JSONPath Finder
	}														//gpath (groovy) in JSON
	
	@Test(priority=4)
	public void testMultipleContent() {
		given()
		.when()
			.get("http://ergast.com/api/f1/drivers.json") //no id //.get("https://reqres.in/api/users/12")
		.then()
			.statusCode(200)
			.body("MRData.DriverTable.Drivers.driverId", hasItems("amick","acheson","alboreto"));  
	}		
	
	@Test(priority=5)
	public void testParamAndHeaderRequestContent() {
		given()
			.param("name","Poland")
	//		.header("","")
		.when()
			.get("https://restcountries.eu/rest/v2") //no id //.get("https://reqres.in/api/users/12")
		.then()
			.statusCode(200)
			.body("[178].currencies[0].name", equalTo("Polish złoty"));  
	}

}
