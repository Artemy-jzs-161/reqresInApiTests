package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.TestSpecs.*;

@DisplayName("API test for REQRES")
public class DeleteUserTests extends TestBase {

    @Test
    @DisplayName("Check code 204 when delete user, method DELETE")
    void checkStatusCodeWhenDeleteUserTests() {
        String deleteResponse =
                step("Make Request", () ->
                        given(requestSpecification)
                                .when()
                                .delete("/api/users/{id}", 2)
                                .then()
                                .spec(resSpecCode204)
                                .extract().asString()
                );
        step("Check Response", () -> assertTrue(deleteResponse.isEmpty()));


    }
}
