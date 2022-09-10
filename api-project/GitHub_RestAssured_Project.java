import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GitHub_RestAssured_Project {
    RequestSpecification reqSpec;
    String sshKey;
    int sshId;

    @BeforeClass
    public void setUp(){
       reqSpec= new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
               .addHeader("Authorization","token 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")
                .setBaseUri("https://api.github.com")
                .build();
    }

    @Test
    public void addSSHKey(){
        String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIIeSqm9M2CAScXJVJCt3T261g9C6wWGHv4GwH578y/lW\"}";


        Response res= given().spec(reqSpec).body(reqBody).when().post("/user/keys");
        System.out.println(res.body().asPrettyString());
        res.then().statusCode(201);
        res.then().body("title",equalTo("TestAPIKey"));
        sshId=res.then().extract().path("id");
    }

    @Test
    public void getSSHKey(){
        Response res=given().spec(reqSpec).when().get("/user/keys");
        System.out.println(res.getBody().asPrettyString());
        res.then().statusCode(200);
        Reporter.log("Response of get request is--> "+res.getBody().asPrettyString());
    }

    @Test
    public void deleteSSHKey(){
        Response res= given().spec(reqSpec).pathParams("keyId",sshId).when().delete("/user/keys/{keyId}");
        System.out.println(res.getBody().asPrettyString());
        res.then().statusCode(204);
        Reporter.log("Response of get request is--> "+res.getBody().asPrettyString());
    }
}
