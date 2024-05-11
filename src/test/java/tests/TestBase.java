package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;


public class TestBase {
    @BeforeAll
    @DisplayName("API tests in reqres.in")
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath ="api/";

    }
}
