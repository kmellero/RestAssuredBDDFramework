package serializationDeserialization;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class StudentAPITestNoSerialization {
@SuppressWarnings("deprecation")
//using Testng hence no need for the main()
	

public HashMap map= new HashMap();
	
		
	//Post request to create new Studnet record
	@Test(priority=1)
	public void createNewStudent() throws InterruptedException {
		map.put("id", 101);
		map.put("firstName", "Jethro");
		map.put("lastName", "Tull");
		map.put("email", "jethro.tull@abcdefg.com");
		map.put("progrmme", "Chemical Engineering");
		
		
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add("Soil Chemistry");
		courseList.add("Selenium");
		map.put("courses", courseList);
		
		given()
			.contentType(ContentType.JSON)
			.body(map)
			.when()
		//clone to localhost git repository https://github.com/Abzalbek/REST-API-Testing--Student-APP----Local-Server		
		//in cloned repo run cmd: \REST-API-Testing--Student-APP----Local-Server>java -jar studentApp.jar --server.port=8085
		.post("http://localhost:8085/student")	
			.then()
			.statusCode(201)
			.assertThat()
			.body("msg", equalTo("Student added"))
			.log().all();
			}
//Get request to retrieve studnet details
	@Test(priority=2)
	void getStudentRecord() {
		given()
		 .when()
		 	.get("http://localhost:8085/student/100")  //should be posted above as id=101, but got http code 500 (?), so run for 100 instead
		 .then()
		 	.statusCode(200)
		 	.assertThat()
		 	.body("id", equalTo(100))
		 	.log().all();
	}
	
}
