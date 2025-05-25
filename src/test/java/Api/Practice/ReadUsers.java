package Api.Practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadUsers {

    @Test
    public void getRequest()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";
            String apiEndpoint = "/registrationData";

            Response response = given()
                    .log().all()
                    .when()
                    .get(apiEndpoint)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(),200);
            System.out.println("Response Status Code : " + response.getStatusCode());
            System.out.println("Ressponse Body : " + response.getBody().toString());
        } catch (Exception e) {
            System.out.println("Failed to read the users by Get Request");
            e.printStackTrace();
        }
    }
}
