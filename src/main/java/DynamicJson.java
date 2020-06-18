import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test
    public void addBook() {
       String response = RestAssured.baseURI = "http://216.10.245.166";
        given().log().all()
                .header("Content-Type", "application/json")
                .body(Payload.addBook())
        .when().post("/Library/Addbook.php")
        .then().log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jp = new JsonPath(response);
        Assert.assertEquals(jp.getString("Msg"), "successfully added");


    }
}
