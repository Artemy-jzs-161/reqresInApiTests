package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@DisplayName("API тесты на REQRES")
public class ReqresInTests {
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    @DisplayName("Проверка получения пользователя по id, метод GET")
    void getSingleUserTests() {
        given()
                .log().uri()
                .when()
                .get("/api/users/{id}", 2)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.email", is("janet.weaver@reqres.in"));

    }

    @Test
    @DisplayName("Проверка, что в запросах к списку юзеров id не пустой, метод GET")
    void getSingleListUsersTests() {
        given()
                .when()
                .contentType(JSON)
                .get("/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("data.id", everyItem(notNullValue()));
    }

    @Test
    @DisplayName("Проверка создания пользователя, метод POST")
    void checkCreateUserTests() {

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/api/users/")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", notNullValue());

    }

    @Test
    @DisplayName("Проверка обновления пользователя, метод PATCH")
    void checkUpdateUserJobTests() {

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .patch("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue());
    }

    @Test
    @DisplayName("Проверка статус кода 204, при удалении пользователя, метод DELETE")
    void checkStatusCodeWhenDeleteUserTests() {

        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .delete("/api/users/10")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}

