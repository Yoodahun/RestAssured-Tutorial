import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(Payload.addBook(isbn,aisle))
        .when().post("/Library/Addbook.php")
        .then().log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jp = new JsonPath(response);
        Assert.assertEquals(jp.get("Msg"), "successfully added");
    }

    //delete book
    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][]{
            {"asdds", "9321"},
            {"deer", "2213"},
            {"panda", "323"}

        };

    }


}
