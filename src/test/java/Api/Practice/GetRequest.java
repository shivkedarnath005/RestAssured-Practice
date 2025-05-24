package Api.Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetRequest
{
    @Test
    public void readUsers()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            Response response = given()
                    .log().all()
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .time(lessThan(2000L))
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println("Response Status Code  : " + response.getStatusCode());
            System.out.println("Response Body : " + response.getBody().asString());

        } catch (Exception e) {
            System.out.println("Failed to read users using Get Request");
            e.getMessage();
        }
    }
}
