package com.qa.rest.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/* Verifying Single content in XML Response
 * Verifying Multiple content in XML Response
 * Verifying all the content in XML Response in one go
 * Find values using XML Path in XML Response
 * XPath with text() function
 */
public class BasicValidations_XML {

//	Verifying Single content in XML Response
	
	@Test(priority=1)
	void testSingleContent() {
		
		given()
			
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
			.log().all()
			.body("CUSTOMER.ID",equalTo("15"));
		
	}
	
	//Verifying Multiple content in XML Response
	@Test(priority=2)
	void testMultipleContents() {

		given()

		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
			.body("CUSTOMER.ID",equalTo("15"))
			.body("CUSTOMER.FIRSTNAME",equalTo("Bill"))
			.body("CUSTOMER.LASTNAME",equalTo("Clancy"))
			.body("CUSTOMER.STREET", equalTo("319 Upland Pl."))
			.body("CUSTOMER.CITY",equalTo("Seattle"));
	}
	
	//Verifying all the content in XML Response in one go; used text()
	@Test(priority=3)
	void testMultipleContentsInOneGo() {

		given()

		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
			.body("CUSTOMER.text()",equalTo("15BillClancy319 Upland Pl.Seattle")); //text() is XPath method

	}	
	
	//Find values using XML Path (XPath) in XML Response
	@Test(priority=4)
	void testUsingXPath1() {
		
		given()
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
			.body(hasXPath("/CUSTOMER/LASTNAME", containsString("Clancy")));
	}
	
	@Test(priority=5)
	void testUsingXPath2() {
		
		given()
		.when()
			.get("http://thomas-bayer.com/sqlrest/INVOICE")
		.then()
			.body(hasXPath("/INVOICEList/INVOICE[text()='30']"));
	}

	
}
