package serializationDeserialization;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

public class VideoGameAPITestWithSerializationJSON {

// download https://github.com/james-willett/VideoGameDB
// install gradle from https://gradle.org/install/#manually
// cmd in the VideoGameDB dir: gradlew bootRun  , wait until it completes and leave cmd running

	@Test(priority=1)
	public void testVideoGameSerializationJSON() {
		VideoGame myVideoGame=new  VideoGame();
		myVideoGame.setId(12);
		myVideoGame.setName("mell123");
		myVideoGame.setReleaseDate("2018-12-11");
		myVideoGame.setReviewScore(11);
		myVideoGame.setCategory("Runt");
		myVideoGame.setRating("Two");
		
		given()
			.contentType("application/json")
			.body(myVideoGame)
		.when()
			.post("http://localhost:8080/app/videogames")
		.then()
			.log().all()
			.body(equalTo("{\"status\": \"Record Added Successfully\"}"));
	}
	
	@Test(priority=2)
	public void testVideoGameDeSerializationJSON() {
		VideoGame myVideoGame = get("http://localhost:8080/app/videogames/12").as(VideoGame.class);
		System.out.println(myVideoGame.toString());  //toString method from VideoGame class
		
	}
}
