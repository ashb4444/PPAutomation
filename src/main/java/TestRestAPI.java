import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestRestAPI {

	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://reqres.in/";
		Response response = RestAssured.given()
          		.headers("Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
          		.get("api/unknown");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
		System.out.println(response.jsonPath().getString("per_page"));
	}
}
