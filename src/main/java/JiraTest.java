import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JiraTest {

    @Test
    public void addComment() {
        RestAssured.baseURI ="https://dahun-practice.atlassian.net";
        String response = given().log().all().pathParam("key", "AP-5")
                .header("Content-type", "application/json")
                .header("User-Agent", "PostmanRuntime/7.25.0")
                .auth().preemptive().basic("tty4032@likelion.org", "hsT1B4ADk2m02Rf4y8jK8415")
                .body("{\n" +
                        "  \"body\": {\n" +
                        "    \"type\": \"doc\",\n" +
                        "    \"version\": 1,\n" +
                        "    \"content\": [\n" +
                        "      {\n" +
                        "        \"type\": \"paragraph\",\n" +
                        "        \"content\": [\n" +
                        "          {\n" +
                        "            \"text\": \" Test Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\",\n" +
                        "            \"type\": \"text\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}")
                .post("/rest/api/3/issue/{key}/comment").then().log().all()
                .assertThat().statusCode(201).extract().response().asString();
        System.out.println(response);


    }



}
