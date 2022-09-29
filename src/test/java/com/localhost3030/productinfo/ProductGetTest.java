package com.localhost3030.productinfo;

import com.localhost3030.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductGetTest extends TestBase {

    @Test
    public void getAllProductsInfo (){
        Response response = given()
                .when()
                .get("");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getSingleProductInfo(){
        Response response = given()
                .pathParam("id", "127687")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // check deleted id is deleted
    public void getDeletedConfirmDeleteChecked (){
        Response response = given()
                .pathParam("id", "9999699")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
