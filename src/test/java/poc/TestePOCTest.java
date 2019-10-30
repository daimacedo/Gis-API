package poc;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

public class TestePOCTest {


//	@Test
//	public void Teste() {
//
//		String uriBase = "https://postman-echo.com/get";
//
//		given().relaxedHTTPSValidation().param("foo1", "bar1").param("foo2", "bar2").when().get(uriBase).then()
//				.statusCode(200).contentType(ContentType.JSON).body("headers.host", is("postman-echo.com"))
//				.body("args.foo1", containsString("bar1"));
//
//	}
//
//	@Test
//	public void testePost() throws IOException {
//
//        String path = new File("src/test/resources/teste/post.json").getAbsolutePath();
//        FileReader fReader = new FileReader(path);
//        Gson gson = new Gson();
//        JsonElement json = gson.fromJson(fReader, JsonElement.class);
//        String jsonInString = gson.toJson(json);
//        System.out.println(jsonInString);
//
//
//		given()
//				.contentType(ContentType.JSON)
//				.body(jsonInString)
//                .when()
//				.post("http://localhost:3000/comments")
//				.then()
//				.statusCode(201);
//
//	}
}
