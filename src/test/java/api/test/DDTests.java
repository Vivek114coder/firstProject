package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilites.Dataproviders;
import io.restassured.response.Response;

public class DDTests {


@Test(priority=1,dataProvider="Data",dataProviderClass=Dataproviders.class)
public void testPostUser(String ID, String username, String fname, String lname, String useremail, String pwd, String ph ) {
	User userpayload= new User();
	userpayload.setId(Integer.parseInt(ID));
	userpayload.setUsername(username);
	userpayload.setFirstname(fname);
	userpayload.setLastname(lname);
	userpayload.setEmail(useremail);
	userpayload.setPassword(pwd);
	userpayload.setPhone(ph);
	
	Response response= UserEndPoint.createUser(userpayload);
	Assert.assertEquals(response.getStatusCode(), 200); 
	
	
}

@Test(priority=2,dataProvider="UserNames",dataProviderClass=Dataproviders.class)
public void DeleteUser( String username) {

	
	Response response= UserEndPoint.deleteUser(username);
	Assert.assertEquals(response.getStatusCode(), 200); 
	
	
}
}
