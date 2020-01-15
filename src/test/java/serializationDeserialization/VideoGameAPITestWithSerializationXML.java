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
