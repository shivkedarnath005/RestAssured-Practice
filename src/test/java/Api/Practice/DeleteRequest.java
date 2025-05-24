package Api.Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequest {

    @Test
    public void deleteUser()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            Response response = given()
                    .pathParam("id", "d57d")
                    .log().all()
                    .when()
                    .delete("/users/{id}")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println("Response Status Code : " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Failed to delete the user record");
            e.printStackTrace();
        }

    }
}
