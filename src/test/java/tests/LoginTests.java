package tests;

import io.restassured.RestAssured;
import models.lombok.LoginBodyModelLombok;
import models.lombok.LoginResponseModelLombok;
import models.lombok.MissingPasswordModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.*;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.*;

public class LoginTests {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    @DisplayName("Проверка успешной регистрации, метод POST")
    void successfullLoginTestPojo() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel responseModel = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()

                .body(authData)
                .contentType(JSON)

                .when()
                .post("/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responseModel.getToken());
    }

    @Test
    @DisplayName("Проверка успешной регистрации, метод POST")
    void successfullLoginLombokTest() {

        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok responseModel = step("make request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(authData)
                        .contentType(JSON)
                        .when()
                        .post("/api/login")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseModelLombok.class));

        step("check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", responseModel.getToken()));
    }

    @Test
    @DisplayName("Проверка успешной регистрации, метод POST")
    void successfullLoginLombokWithSpecTest() {

        LoginBodyModelLombok authData = new LoginBodyModelLombok();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok responseModel =
        step("make request", () ->
                given(loginReqSpec)
                        .body(authData)
                .when()
                        .post()
                .then()
                        .spec(loginResSpec)
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
                        given(loginReqSpec)
                                .body(authData)

                                .when()
                                .post()

                                .then()
                                .spec(missingPasswordResSpec)
                                .extract().as(MissingPasswordModel.class));

        step("check response", () ->
                assertEquals("Missing password ", responseModel.getError()));
    }
}
