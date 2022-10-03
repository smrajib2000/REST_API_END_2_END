package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import POJO.Location;
import POJO.addPlace;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources_enum;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefination extends Utils{
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Add place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws FileNotFoundException {
		
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}



	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//constructor will be called 
		APIResources_enum resourceAPI  = APIResources_enum.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equals("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if(method.equals("GET"))
			response = res.when().get(resourceAPI.getResource());
		else if(method.equals("PUT"))
			response = res.when().put(resourceAPI.getResource());
		else if(method.equals("DELETE"))
			response = res.when().delete(resourceAPI.getResource());
			
		//.then().spec(resSpec).extract().response();			
	}


	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	}


	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws FileNotFoundException {
		
		//request spec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws FileNotFoundException {
		
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}







}
