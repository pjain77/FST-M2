package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    String baseURI="https://petstore.swagger.io/v2/user";

    @Test
    public void postRequest() throws IOException {
        File file = new File("src/test/java/activities/userinfo.json");
        FileInputStream inputJSON = new FileInputStream(file);
        // Get all bytes from JSON file
        byte[] bytes = new byte[(int) file.length()];
        inputJSON.read(bytes);
        // Read JSON file as String
        String reqBody = new String(bytes, "UTF-8");
        Response res= given().contentType(ContentType.JSON).body(reqBody).when().post(baseURI);
        res.then().statusCode(200);
    }

    @Test
    public void getRequest(){
        System.out.println(given().when().get(baseURI+"/pjain").prettyPrint());

        Response res=given().pathParams("username","pjain").when().get(baseURI+"/{username}");
        System.out.println(res.getBody().prettyPrint());
        res.then().assertThat().extract().path("password").toString().equals("passwhrd123");
        res.then().body("password",equalTo("passwhrd123"));
        //res.then().extract().path("password").equals("passwhrd123");
    }

    @Test
    public void deleteRequest(){
        Response res=given().contentType(ContentType.JSON).pathParam("username","pjain").when().delete(baseURI+"/{username}");
        res.then().body("code",equalTo(200));
    }
}
