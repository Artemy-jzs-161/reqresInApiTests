package tests;

import io.qameta.allure.Owner;
import models.UserResponse;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.TestSpecs.*;


@DisplayName("Checking single user")
public class SingleUserTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking user receipt by id, GET method")
    @Test
    void singleUserTest() {
        UserResponse userResponse =
                step("Make request", () ->
                        given(requestSpecification)
                                .when()
                                .get("users/{id}", 2)
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(UserResponse.class));

        step("Verify single user data", () -> {
            assertEquals(2, userResponse.getUserModel().getId());
            assertEquals("janet.weaver@reqres.in", userResponse.getUserModel().getEmail());
            assertEquals("Weaver", userResponse.getUserModel().getLastName());
            assertEquals("https://reqres.in/img/faces/2-image.jpg", userResponse.getUserModel().getAvatar());
            assertEquals("https://reqres.in/#support-heading", userResponse.getSupportInformationModel().getUrl());
            assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", userResponse.getSupportInformationModel().getText());
        });
    }
}
