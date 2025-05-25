package Api.Practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUser {

    @Test
    public void postRequest()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            JSONObject payload = new JSONObject();
            payload.put("firstName", "Ravi");
            payload.put("lastName", "Sharma");
            payload.put("dateOfBirth", "1990-03-12");
            payload.put("email", "ravisharma@gmail.com");
            payload.put("phoneNumber", "9860935084");
            payload.put("addressCity", "Pune");
            payload.put("programmingLanguage", "Java");
            payload.put("engineeringSubject", "Artificial Intelligence");
            System.out.println(payload.toJSONString());

            Response response = given()
                    .header("Content-Type","application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload.toJSONString())
                    .log().all()
                    .when()
                    .post("/registrationData")
                    .then()
                    .statusCode(201)
                    .log().all()
                    .extract().response();
            Assert.assertEquals(response.getStatusCode(),201);
            System.out.println("Response Status Code : " + response.getStatusCode());
            System.out.println("Response Body : " + response.getBody().toString());
        } catch (Exception e) {
            System.out.println("Failed to Create the new user resource using POST request");
            e.printStackTrace();
        }

    }
}
