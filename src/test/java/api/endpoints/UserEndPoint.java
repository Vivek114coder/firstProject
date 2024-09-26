package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint {
public static Response  createUser(User PayLoad) {
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(PayLoad)
			.when()
			.post(Routes.post_url);
	
	return response;
			
}

public static Response  readUser(String username) {
	Response response=given()
			.pathParam("username", username)
			.when()
			.get(Routes.Get_url);
	
	return response;
			
}

public static Response  updateUser(String username, User PayLoad) {
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(PayLoad)
			.when()
			.put(Routes.put_url);
	
	return response;
			
}

public static Response  deleteUser(String username) {
	
	Response response= given()
			.pathParam("username", username)
			.when()
			.delete(Routes.Delet_urle);
	return response;
			

			
}
}
