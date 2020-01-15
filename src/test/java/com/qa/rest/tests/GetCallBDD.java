package com.qa.rest.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCallBDD {


	@Test
	public void test_numberOfCircuitsFor2017_Season() {

		//the format in sequence is 
		//		given().
		//		when().
		//		then().
		//		assert()

		given().
		when().
			get("http://ergast.com/api/f1/2019/circuits.json").
		then().
			assertThat().
			statusCode(200).
			and().
			body("MRData.CircuitTable.Circuits.circuitId", hasSize(21)).
			and().
			header("Content-Length", equalTo("4739"));

		

	}
}
