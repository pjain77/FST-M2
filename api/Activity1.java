package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;

public class Activity1 {
    String baseURI="https://petstore.swagger.io/v2/pet";

    @Test
    public void postRequest() {
        String reqBody = "{"
                + "\"id\": 77232,"
                + "\"name\": \"Riley\","
                + " \"status\": \"alive\""
                + "}";
        // Making POST request
        Response res = given().contentType(ContentType.JSON).body(reqBody).when().post(baseURI);
        res.then().body("id", equalTo(77232));
        res.then().body("name", equalTo("Riley"));
        res.then().body("status", equalTo("alive"));
    }

        @Test
        public void getRequest(){
            Response res=given().contentType(ContentType.JSON).when().get(baseURI+"/77232");
            System.out.println(res.body().prettyPrint());
            res.then().statusCode(200);
            res.then().extract().path("status").equals("sold");
        }

        @Test
        public void deleteRequest(){
        Response res= given().contentType(ContentType.JSON).when().delete(baseURI+"/77232");
            System.out.println(res.body().prettyPrint());
            res.then().extract().path("code").equals(200);
    }
    }

