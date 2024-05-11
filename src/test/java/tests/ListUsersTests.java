package tests;

import io.qameta.allure.Owner;
import models.ListUsersResponseModel;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.TestSpecs.*;

@DisplayName("Checking list users")
public class ListUsersTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking second user in the List, GET method")
    @Test
    void SecondUserInTheListTest() {
        ListUsersResponseModel listUsersResponseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .param("page", 2)
                                .when()
                                .get("users")
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(ListUsersResponseModel.class));

        step("Verify second single user in array", () -> {
            assertEquals(8, listUsersResponseModel.getUserModel().get(1).getId());
            assertEquals("lindsay.ferguson@reqres.in", listUsersResponseModel.getUserModel().get(1).getEmail());
            assertEquals("Lindsay", listUsersResponseModel.getUserModel().get(1).getFirstName());
            assertEquals("Ferguson", listUsersResponseModel.getUserModel().get(1).getLastName());
        });
    }

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking the display of six users on the page, GET method")
    @Test
    void countUsersOnPageTest() {
        ListUsersResponseModel listUsersResponseModel =
                step("make request", () ->
                        given(requestSpecification)
                                .param("page", 2)
                                .when()
                                .get("users")
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(ListUsersResponseModel.class));

        step("Checking the number of users per page", () -> {
            assertEquals(6, listUsersResponseModel.getUserModel().size());
        });
    }
}


