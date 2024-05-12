package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.ResponseSpecBuilder;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class TestSpecs {

    public static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static ResponseSpecification resSpecCode200 = new ResponseSpecBuilder().
            expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification resSpecCode201 = new ResponseSpecBuilder().
            expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification resSpecCode204 = new ResponseSpecBuilder().
            expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification resSpecCode400 = new ResponseSpecBuilder().
            expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();
}
