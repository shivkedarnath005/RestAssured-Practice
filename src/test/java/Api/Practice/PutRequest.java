package Api.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequest {

    @Test
    public void updateUser()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            JSONObject payload = new JSONObject();
            payload.put("firstName", "Xi");
            payload.put("lastName", "Jinping");
            payload.put("language", "Politician");
            payload.put("subjectId", 4);
            System.out.println(payload.toJSONString());

            Response response = given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("id", "d57d")
                    .body(payload.toJSONString())
                    .log().all()
                    .when()
                    .put("/users/{id}")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println("Response Status Code : " + response.getStatusCode());
            System.out.println("Response Body : " + response.body().toString());
        } catch (Exception e) {
            System.out.println("Failed to update the user details");
            e.printStackTrace();
        }
    }
}
