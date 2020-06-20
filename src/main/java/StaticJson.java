import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {

    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(generaterStringFromResource("src/main/resources/addBook.json"))
                .when().post("/Library/Addbook.php")
                .then().log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jp = new JsonPath(response);
        Assert.assertEquals(jp.get("Msg"), "successfully added");
    }

    public static String generaterStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
