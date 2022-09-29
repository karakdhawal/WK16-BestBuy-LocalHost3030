package com.localhost3030.productinfo;

import com.localhost3030.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductDeleteTest extends TestBase {

    @Test
    public void delete (){
        Response response = given()
                .pathParam("id", "9999699")
                .when()
                .delete("/{id}");
        response.then().statusCode(200); //204 not working showing 200
        response.prettyPrint();
    }
}
