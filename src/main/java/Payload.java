public class Payload {

    public static String AddPlace() {
        return "{\n" +
                "\"location\": {\n" +
                "\"lat\": -38.383494, \"lng\": 33.427362\n" +
                "},\n" +
                "\"accuracy\": 50,\n" +
                "\"name\": \"Frontline house\", \"phone_number\": \"(+91) 983 893 3937\", \"address\": \"29, side layout, cohen 09\", \"types\": [\n" +
                "\"shoe park\", \"shop\"\n" +
                "  \n" +
                "],\n" +
                "\"website\": \"http://google.com\", \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String coursePrice() {
        return "{\n" +
                "\"dashboard\": {\n" +
                "\"purchaseAmount\": 910,\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "},\n" +
                "\"courses\": [\n" +
                "{\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\"price\": 50,\n" +
                "\"copies\": 6\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Cypress\",\n" +
                "\"price\": 40,\n" +
                "\"copies\": 4\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"RPA\",\n" +
                "\"price\": 45,\n" +
                "\"copies\": 10\n" +
                "}\n" +
                "]\n" +
                "}";
    }

    public static String addBook() {
        return "{\n" +
                "\"name\":\"Learn Appium Automation with Java\", \"isbn\":\"bcd\",\n" +
                "\"aisle\":\"227\",\n" +
                "\"author\":\"John foe\"" +
                "\n}";
    }




}
