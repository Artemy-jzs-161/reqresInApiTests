package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.TestSpecs.*;

@DisplayName("Check delete user")
public class DeleteUserTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Check code 204 when delete user, DELETE method")
    @Test
    void statusCodeWhenDeleteUserTest() {
        String deleteResponse =
                step("Make Request", () ->
                        given(requestSpecification)
                                .when()
                                .delete("users/{id}", 2)
                                .then()
                                .spec(resSpecCode204)
                                .extract().asString()
                );
        step("Check Response", () -> assertTrue(deleteResponse.isEmpty()));


    }
}
