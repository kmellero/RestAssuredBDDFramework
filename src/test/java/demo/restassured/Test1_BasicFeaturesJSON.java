package demo.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class Test1_BasicFeaturesJSON {

	//check status code
	@Test(priority=1)
	public void testStatuscode() {
		given()
			.get("http://jsonplaceholder.typicode.com/posts/3")
		.then()
			.statusCode(200);
	}

	//verify code and print complete response in console
	@Test(priority=2)
	public void testStatusAndBody() {
		given()
			.get("https://reqres.in/api/users/3")
		.then()
			.statusCode(200)
			.log().all();
	}
	//check single content with org.hamcrest.Matchers library equalTo method
	@Test(priority=3)
	public void testEqualToMethod() {
		given()
			.contentType("application/json")
		 	.get("https://reqres.in/api/users/3")
		.then()
			.assertThat()
			.body("data.first_name", equalTo("Emma"));
	}
	
	//multiple content using org.hamcrest.Matchers library
	@Test(priority=4)
	public void testHasItemsMethod() {
		given()
			.get("https://restcountries.eu/rest/v2/all")
		.then()
			.body("name", hasItems("Afghanistan","Andorra","Poland"));
	}
	
	//set parameters and header in the request
	@Test(priority=5)
	public void testParamAndHeaders() {
		given()
			.queryParam("alpha2Code", "PL")
//		.param("alpha2Code", "PL")
		 	.and()
		 	.header("Content-Type","application/json")
		.when().get("https://restcountries.eu/rest/v2/all")
		.then()
			.assertThat()
			.statusCode(200)
			.body("[178].callingCodes[0]", equalTo("48"))
			.log().all();  //Poland, used JSON Path Finder for chrome
	}
	
}
