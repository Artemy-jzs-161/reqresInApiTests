package tests;

import io.restassured.specification.ResponseSpecification;
import models.ListUsersResponseModel;
import models.UserResponse;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.google.common.base.Predicates.equalTo;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.sessionId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.SingleUserSpec.UserRequestSpec;
import static specs.SingleUserSpec.UsersListResponseSpec;

@DisplayName("API test for REQRES")
public class ListUsersTests extends TestBase {

    @Test
    @DisplayName("Checking that in requests to the list of users the id is not empty, method GET")
    void getSingleListUsersTests() {
        ListUsersResponseModel listUsersResponseModel =
                step("make request", () ->
                        given(UserRequestSpec)
                                .param("page", 2)
                                .when()
                                .get("/api/users")
                                .then()
                                .spec(UsersListResponseSpec)
                                .extract().as(ListUsersResponseModel.class));

        step("verify second single user in array", () -> {
            assertEquals(8, listUsersResponseModel.getUserModel().get(1).getId());
            assertEquals("lindsay.ferguson@reqres.in", listUsersResponseModel.getUserModel().get(1).getEmail());
            assertEquals("Lindsay", listUsersResponseModel.getUserModel().get(1).getFirstName());
            assertEquals("Ferguson", listUsersResponseModel.getUserModel().get(1).getLastName());
        });

        step("checking the number of users per page", () -> {
            assertEquals(6, listUsersResponseModel.getUserModel().size());

        });
    }
}


