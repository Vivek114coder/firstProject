package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;
import jdk.internal.org.jline.utils.Log;

public class UserTests {

	Faker faker;
	User userpayload;
	public Logger logger; 
	@BeforeClass
	public void SetupData() {
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	
		
		
	}
	
	@Test(priority=1)
	public void  testPostuser() {
	
		Response response= UserEndPoint.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		
		
	}
	
	@Test(priority=2)
	public void  testGetuser() {
		
		Response response= UserEndPoint.readUser(this.userpayload.getUsername());
	    response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		
		
	}
	
	
	@Test(priority=2)
	public void  testUpdateuser() {
		
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response= UserEndPoint.updateUser(this.userpayload.getUsername(), userpayload);
	    response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		Response responseAferUpdate=UserEndPoint.readUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=3)
	public void  testDeleteuser() {
	
		Response response= UserEndPoint.deleteUser(this.userpayload.getUsername());
	    response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		
		
	}

	
	}
	
	

