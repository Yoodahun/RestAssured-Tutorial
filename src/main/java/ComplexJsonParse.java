import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    //Diving in Depth-Automating REST API's
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());

        //1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //2.Print Purchase Amount
        System.out.println(js.getInt("dashboard.purchaseAmount"));

        //3. Print Title of the first course
        System.out.println(js.getString("courses[0].title"));

        //4. Print all course titles and their respective prices

        for (int i = 0;i<count;i++) {
            System.out.println(js.getString("courses[" + i + "].title"));
            System.out.println(js.getString("courses[" + i + "].price"));
        }

        //5. Print No of copies sold by RPA course

        for (int i = 0;i<count;i++) {
            if(js.getString("courses[" + i + "].title").contains("RPA")) {
                System.out.println(js.getString("courses[" + i + "].copies"));
                break;
            }
        }

        //6. Verify if sum of all course prices mathces with purchase amount.
        int total = 0;
        for (int i = 0;i<count;i++) {
            total += ( js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies"));

            System.out.println(total);

        }
        Assert.assertEquals(total, js.getInt("dashboard.purchaseAmount"));



    }
}
