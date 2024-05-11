package tests;

import models.UpdateUserRequestModel;
import models.UpdateUsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.TestSpecs.*;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("API test for REQRES")
public class UpdateUserTests extends TestBase {
    @Test
    @DisplayName("Checking user update, method PATCH")
    void checkUpdateUserJobTests() {
        UpdateUserRequestModel updateUserRequestModel = new UpdateUserRequestModel();
        updateUserRequestModel.setName("morpheus");
        updateUserRequestModel.setJob("zion resident");

        UpdateUsersResponseModel updateUsersResponseModel =
                step("make request", () ->
                        given(requestSpecification)
                                .body(updateUserRequestModel)
                                .when()
                                .patch("/api/users/{id}", 2)
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(UpdateUsersResponseModel.class));

        step("check name, job", () -> {
            assertEquals("morpheus", updateUsersResponseModel.getName());
            assertEquals("zion resident", updateUsersResponseModel.getJob());
        });
    }
}
