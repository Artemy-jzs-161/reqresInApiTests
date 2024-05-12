package tests;

import io.qameta.allure.Owner;
import models.LoginBodyModelLombok;
import models.LoginResponseModelLombok;
import models.MissingPasswordModel;

import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.TestSpecs.*;

@DisplayName("Check login tests")
public class LoginTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking successful login, POST method")
    @Test
    void successfulLoginTest() {
        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok responseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .body(authData)
                                .when()
                                .post("login")
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(LoginResponseModelLombok.class));

        step("Check response", () ->
                assertNotNull(responseModel.getToken()));
    }

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking missing login, POST method")
    @Test
    void missingPasswordTest() {
        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");

        MissingPasswordModel responseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .body(authData)
                                .when()
                                .post("login")
                                .then()
                                .spec(resSpecCode400)
                                .extract().as(MissingPasswordModel.class));

        step("Check response", () ->
                assertEquals("Missing password", responseModel.getError()));
    }
}
