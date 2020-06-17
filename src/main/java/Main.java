import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.Assert;

import static io.restassured.RestAssured.*;


public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add place
        String response = given().log().all()
                                .queryParam("key", "qaclick123")
                                .header("Content-Type", "application/json")
                                .body(Payload.AddPlace())
                        .when().post("/maps/api/place/add/json")
                        .then().log().all()
                                .statusCode(200)
                                .body("scope", Matchers.equalTo("APP"))
                                .header("Server", Matchers.equalTo("Apache/2.4.18 (Ubuntu)"))
                                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        System.out.println(place_id);

        String newAddress = "70 Summer walk, USA";

        //update place
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\"place_id\": \"" + place_id + "\"," +
                        "\"address\": "+"\"" + newAddress + "\"," +
                        "\"key\": " + "\"qaclick123\""+
                        "}"

                )
        .when().put("/maps/api/place/update/json")
        .then().log().all()
                .assertThat().statusCode(200)
                .body("msg", Matchers.equalTo("Address successfully updated"));

        //get place
        String getPlace = given().log().all()
                                .queryParam("key", "qaclick123")
                                .queryParam("place_id", place_id)
                        .when().get("/maps/api/place/get/json")
                        .then().log().all()
                                .assertThat()
                                .statusCode(200)
                                .body("address", Matchers.equalTo(newAddress))
                                .extract().response().asString();

        JsonPath getJSPlace = new JsonPath(getPlace);
        System.out.println(getJSPlace.getString("address"));
        Assert.assertEquals(
                new JsonPath(getPlace).getString("address"),
                "hhhh"
        );



    }
}
