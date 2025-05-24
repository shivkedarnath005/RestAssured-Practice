package Api.Practice;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequest {

    @Test
    public void createUser()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            JSONObject payload = new JSONObject();
            payload.put("firstName", "Kim");
            payload.put("lastName", "Jong");
            payload.put("subjectId", 5);
            payload.put("language", "Politics");
            System.out.println(payload.toJSONString());

            Response response = given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                    .log().all()
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(), 201);
            System.out.println("Response Status Code : " + response.getStatusCode());
            System.out.println("Response Body : " + response.getBody().toString());
        } catch (Exception e) {
            System.out.println("Failed to create user using POST Api Request");
            e.getMessage();
        }
    }
}
