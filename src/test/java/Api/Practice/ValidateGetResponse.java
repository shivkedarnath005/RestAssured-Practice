package Api.Practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class ValidateGetResponse {

    @Test
    public void getResponseValidations()
    {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = given()
                .log().all()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        //Using JsonPath
        JsonPath jsonPath = response.jsonPath();
        String firstName = jsonPath.getString("firstName");
        System.out.println("First Name Of Users : " + firstName);

        //Using extract().path()
        ArrayList<String> language = response
                .then()
                .extract()
                .path("language");
        System.out.println("Language of the All Users : " + language);

        //Extracting Values as Different Data Type for single user
//        int id = response
//                .then()
//                .extract()
//                .path("id");
//        System.out.println("User ID : " + id );

        //Extracting Values as Different List of Data Types
        List<Integer> subjectId =response.jsonPath().getList("subjectId");

        int newId = 5;
        assertTrue(subjectId.contains(newId)); // Ensure newId is unique

    }
}
