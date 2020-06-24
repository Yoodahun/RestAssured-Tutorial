import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraTest {

    static final String EMAIL = "";
    static final String PASSWORD = "";
    String id = null;

//    @Test
    public void addComment() {
        RestAssured.baseURI ="https://dahun-practice.atlassian.net";
        String response = given().log().all().pathParam("key", "AP-5")
                .header("Content-type", "application/json")
//                .header("User-Agent", "PostmanRuntime/7.25.0")
                .auth().preemptive().basic(EMAIL, PASSWORD)
                .body("{\n" +
                        "  \"body\": {\n" +
                        "    \"type\": \"doc\",\n" +
                        "    \"version\": 1,\n" +
                        "    \"content\": [\n" +
                        "      {\n" +
                        "        \"type\": \"paragraph\",\n" +
                        "        \"content\": [\n" +
                        "          {\n" +
                        "            \"text\": \" Test Test Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\",\n" +
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
        JsonPath js = new JsonPath(response);
        id = js.get("id");

    }

//    @Test
    public void addAttachment(){
        RestAssured.baseURI ="https://dahun-practice.atlassian.net";
        String response = given().log().all()
                                 .pathParam("key","AP-5")
                                 .auth().preemptive().basic(EMAIL, PASSWORD)
                                 .header("X-Atlassian-Token", "no-check")
                                 .header("Content-Type", "multipart/form-data")
                                 .multiPart("file", new File("src/main/resources/jiraAttachment.txt"))
                         .when().post("/rest/api/3/issue/{key}/attachments")
                         .then().log().all()
                         .assertThat().statusCode(200)
                         .extract().response().asString();
        System.out.println(response);

    }

    @Test
    public void getIssue() {
        RestAssured.baseURI ="https://dahun-practice.atlassian.net";
        String response = given().log().all()
                                 .pathParam("key", "AP-5")
                                 .queryParam("fields", "comment")
                                 .auth().preemptive().basic(EMAIL, PASSWORD)
                         .when().get("/rest/api/3/issue/{key}")
                         .then().log().all()
                         .extract().response().asString();
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        int count = js.getInt("fields.comment.comments.size()");

        for (int i = 0; i<count; i++) {
            System.out.println(js.getString("fields.comment.comments[" + i + "].body.content.content.text[0]"));

        }


    }







}
