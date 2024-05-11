package tests;

import io.qameta.allure.Owner;
import models.UpdateUserRequestModel;
import models.UpdateUsersResponseModel;
import org.junit.jupiter.api.*;

import static specs.TestSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Checking update user")
public class UpdateUserTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking update user, PATCH method")
    @Test
    void updateUserJobTest() {
        UpdateUserRequestModel updateUserRequestModel = new UpdateUserRequestModel();
        updateUserRequestModel.setName("morpheus");
        updateUserRequestModel.setJob("zion resident");

        UpdateUsersResponseModel updateUsersResponseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .body(updateUserRequestModel)
                                .when()
                                .patch("users/{id}", 2)
                                .then()
                                .spec(resSpecCode200)
                                .extract().as(UpdateUsersResponseModel.class));

        step("Check name, job", () -> {
            assertEquals("morpheus", updateUsersResponseModel.getName());
            assertEquals("zion resident", updateUsersResponseModel.getJob());
        });
    }
}
