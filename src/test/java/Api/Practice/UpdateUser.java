package Api.Practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUser {

    @Test
    public void putRequest()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            JSONObject payload = new JSONObject();
            payload.put("firstName", "Sachin");
            payload.put("lastName", "Jadhav");
            payload.put("dateOfBirth", "1991-08-28");
            payload.put("email", "sachinjadhav@gmail.com");
            payload.put("phoneNumber", "8855045338");
            payload.put("addressCity", "Mumbai");
            payload.put("programmingLanguage", "JavaScript");
            payload.put("engineeringSubject", "Computer Application");
            System.out.println(payload.toJSONString());

            Response response = given()
                    .header("Content-Type","application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload.toJSONString())
                    .pathParam("id","3ea6")
                    .log().all()
                    .when()
                    .put("/registrationData/{id}")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();
            Assert.assertEquals(response.getStatusCode(),200);
            System.out.println("Response Status Code : " + response.getStatusCode());

        } catch (Exception e) {
            System.out.println("Failed to update the user using PUT request");
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser()
    {
        try {
            RestAssured.baseURI = "http://localhost:3000";

            Response response = given()
                    .pathParam("id", "3ea6")
                    .log().all()
                    .when()
                    .delete("/registrationData/{id}")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();
            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println("Response Status Code : " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Failed to Delete the User");
            e.printStackTrace();
        }

    }
}
