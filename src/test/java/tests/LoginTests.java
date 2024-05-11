package tests;

import models.LoginBodyModelLombok;
import models.LoginResponseModelLombok;
import models.MissingPasswordModel;

import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.TestSpecs.*;

@DisplayName("API test for REQRES")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Проверка успешной регистрации, метод POST")
    void successfulLoginLombokWithSpecTest() {

        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok responseModel =
                step("make request", () ->
                        given(requestSpecification)
                                .body(authData)
                                .when()
                                .post("/api/login")
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(LoginResponseModelLombok.class));

        step("check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", responseModel.getToken()));
    }

    @Test
    @DisplayName("Проверка успешной регистрации, метод POST")
    void missingPasswordTest() {
        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");

        MissingPasswordModel responseModel =
                step("make request", () ->
                        given(requestSpecification)
                                .body(authData)

                                .when()
                                .post("/api/login")

                                .then()
                                .spec(missingPasswordResSpec)
                                .extract().as(MissingPasswordModel.class));

        step("check response", () ->
                assertEquals("Missing password", responseModel.getError()));
    }
}
