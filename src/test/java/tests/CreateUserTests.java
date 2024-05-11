package tests;

import io.qameta.allure.Owner;
import models.CreateUsersRequestModel;
import models.CreateUsersResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.TestSpecs.*;

import org.junit.jupiter.api.*;


@DisplayName("Check create user")
public class CreateUserTests extends TestBase {

    @Owner("Artemy-jzs-161")
    @DisplayName("Checking user create, POST method")
    @Test
    void createUserTest() {
        CreateUsersRequestModel createUsersRequestModel = new CreateUsersRequestModel();
        createUsersRequestModel.setName("morpheus");
        createUsersRequestModel.setJob("leader");

        CreateUsersResponseModel createUsersResponseModel =
                step("Make request", () ->
                        given(requestSpecification)
                                .body(createUsersRequestModel)
                                .when()
                                .post("users/")
                                .then()
                                .spec(resSpecCode201)
                                .extract().as(CreateUsersResponseModel.class));

        step("Check name, job, id and createdAt", () -> {
            assertEquals("morpheus", createUsersResponseModel.getName());
            assertEquals("leader", createUsersResponseModel.getJob());
            assertNotNull(createUsersResponseModel.getId());
            assertNotNull(createUsersResponseModel.getCreatedAt());
        });

    }
}
