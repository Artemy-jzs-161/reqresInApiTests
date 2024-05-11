package tests;

import models.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.SingleUserSpec.UserRequestSpec;
import static specs.SingleUserSpec.UsersListResponseSpec;

@DisplayName("API test for REQRES")
public class SingleUserTests extends TestBase {

    @Test
    @DisplayName("Checking user receipt by id, method GET")
    void getSingleUserTests() {
        UserResponse userResponse =
                step("make request", () ->
                        given(UserRequestSpec)
                                .when()
                                .get("/api/users/2")
                                .then()
                                .spec(UsersListResponseSpec)
                                .extract().as(UserResponse.class));

        step("verify single user data", () -> {
            assertEquals(2, userResponse.getUserModel().getId());
            assertEquals("janet.weaver@reqres.in", userResponse.getUserModel().getEmail());
            assertEquals("Weaver", userResponse.getUserModel().getLastName());
            assertEquals("https://reqres.in/img/faces/2-image.jpg", userResponse.getUserModel().getAvatar());
            assertEquals("https://reqres.in/#support-heading", userResponse.getSupportInformationModel().getUrl());
            assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", userResponse.getSupportInformationModel().getText());
        });
    }
}
