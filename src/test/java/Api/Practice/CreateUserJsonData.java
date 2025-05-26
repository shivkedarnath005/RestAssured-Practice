package Api.Practice;

import Utility.JsonDataReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserJsonData {

    @Test
    public void postRequest()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";
            String apiEndpoint = "/registrationData";
            String filePath = "src/test/resources/TestData/userPayload.json";

            String requestBody = JsonDataReader.getJsonFromFile(filePath);

            Response response = given()
                    .header("Content-Type","application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .log().all()
                    .when()
                    .post(apiEndpoint)
                    .then()
                    .statusCode(201)
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(),201);
            System.out.println("Response Status Code : " + response.getStatusCode());

        } catch (Exception e) {
            System.out.println("Failed to Create the new user resource using POST request : " + e.getMessage());
            e.printStackTrace();
        }

    }
}
