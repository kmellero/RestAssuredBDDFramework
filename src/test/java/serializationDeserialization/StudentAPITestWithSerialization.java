package serializationDeserialization;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class StudentAPITestWithSerialization {
	//need java Object with get and setters that is Student
	
	@Test(priority=1)
	public void createNewStudentSerialization() {
		
		ArrayList<String> coursesList = new ArrayList<String>();
		coursesList.add("java");
		coursesList.add("Selenium");
		
		Student stu=new Student();
		stu.setSID(101);
		stu.setFirstName("Jonas");
		stu.setLastName("Cray");
		stu.setEmail("abc123@gmail.com");
		stu.setProgramme("Forestry");
		stu.setCourses(coursesList);
		
		//passing complete object. This is serialization.  This POST works
		given()
		 .contentType(ContentType.JSON)
		 .body(stu)
		 .when()
		 	.post("http://localhost:8085/student")
		 .then()
		 	.statusCode(201)
		 	.assertThat()
		 	.body("msg", equalTo("Student added"));
	}
	
	//Deserialization  
	@Test(priority=2)
	void getStudentRecordDeserialization() {
			
		Student stu = get("http://localhost:8085/student/101").as(Student.class);
		System.out.println(stu.getStudentRecord());
		Assert.assertEquals(stu.getSID(), 101);
	}
	
}
