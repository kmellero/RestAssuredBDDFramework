package demo.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Test1_BasicFeaturesXML {

	//test single body content
	@Test(priority=1)
	public void testSingleContent() {
		given()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/14")
		.then()
			.body("CUSTOMER.ID", equalTo("14"))
			.log().all();
	}
	
	//test xml response, multiple body content
	@Test(priority=2)
	public void testMultipleContent() {
		given()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/14")
		.then()
			.body("CUSTOMER.ID", equalTo("14"))
			.body("CUSTOMER.FIRSTNAME", equalTo("Bill")).body("CUSTOMER.LASTNAME", equalTo("Karsen")).body("CUSTOMER.STREET", equalTo("53 College Av."))
			.body("CUSTOMER.CITY", equalTo("Oslo"));
	}
	
	@Test(priority=3)
	public void testCompleteTextInOneGo() {
		given()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/14")
		.then()
			.body("CUSTOMER.text()", equalTo("14BillKarsen53 College Av.Oslo"))
			.log().all();
	}
	
	//xpath to find value
	@Test(priority=4)
	public void testUsingXpath1() {
		given()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/14")
		.then()
			.body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Bill")));
	}
	
	//xpath types
	@Test(priority=5)
	public void testUsingXpath2() {
		given()
			.get("http://thomas-bayer.com/sqlrest/INVOICE/")
		.then()
			.body(hasXPath("/INVOICEList/INVOICE[text()='40']"));
		
	}
}
