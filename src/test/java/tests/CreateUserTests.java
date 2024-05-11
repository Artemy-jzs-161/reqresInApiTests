package tests;

import models.CreateUsersRequestModel;
import models.CreateUsersResponseModel;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.TestSpecs.requestSpecification;
import static specs.TestSpecs.resSpecCode201;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("API test for REQRES")
public class CreateUserTests extends TestBase {

    @Test
    @DisplayName("Check create users, method POST")
    void checkCreateUserTests() {
        CreateUsersRequestModel createUsersRequestModel = new CreateUsersRequestModel();
        createUsersRequestModel.setName("morpheus");
        createUsersRequestModel.setJob("leader");

        CreateUsersResponseModel createUsersResponseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .body(createUsersRequestModel)
                                .when()
                                .post("/api/users/")
                                .then()
                                .spec(resSpecCode201)
                                .extract().as(CreateUsersResponseModel.class));
        step("Check name, job, id, createdAt", () -> {
            assertEquals("morpheus", createUsersResponseModel.getName());
            assertEquals("leader", createUsersResponseModel.getJob());
            assertNotNull(createUsersResponseModel.getId());
            assertNotNull(createUsersResponseModel.getCreatedAt());
        });

    }
}
