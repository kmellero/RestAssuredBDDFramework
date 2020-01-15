package serializationDeserialization;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class VideoGameAPITestWithSerializationXML {
// download https://github.com/james-willett/VideoGameDB
// install gradle from https://gradle.org/install/#manually
// cmd in the VideoGameDB dir: gradlew bootRun  , wait until it completes and leave cmd running
// after VideoGame is running in cmd, paste in browser url http://localhost:8080/swager-ui/index.html, to see API requests for this webservice
//in swagger, click on List Operations, and see XML Request format, use Try in to verify Response

	
	
	@Test(priority=1)
	public void testVideoGameSerializationXML() {
		VideoGame myVideoGame=new  VideoGame();
		myVideoGame.setId(11);
		myVideoGame.setName("kaj123");
		myVideoGame.setReleaseDate("2019-12-10");
		myVideoGame.setReviewScore(91);
		myVideoGame.setCategory("Racing");
		myVideoGame.setRating("Six");
		
		given()
			.contentType("application/xml")
			.body(myVideoGame)
		.when()
			//get request url from swagger for POST, 
			//e.g. change value of id in request example, "Try it" (send application/xml), get "Request URL", paste it below 
			.post("http://localhost:8080/app/videogames")  
		.then()
			.log().all()
			.body(equalTo("{\"status\": \"Record Added Successfully\"}"));
	}
	
	@Test(priority=2)
	public void testVideoGameDeSerializationXML() {
		VideoGame myVideoGame = get("http://localhost:8080/app/videogames/11").as(VideoGame.class);
		System.out.println(myVideoGame.toString());  //toString method from VideoGame class
		
	}
}
