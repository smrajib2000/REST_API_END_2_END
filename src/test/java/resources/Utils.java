package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		if(reqSpec == null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		//RequestSpecBuilder use for request
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).
				addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqSpec;
		}
		return reqSpec;
		
	}
	
	public static String getGlobalValue(String key) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("E:\\Eclipse with project\\API TESTING\\API_FRAMEWORK\\src\\test\\java\\resources\\global.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
		 
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
