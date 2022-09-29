package com.localhost3030.productinfo;

import com.localhost3030.model.ProductPojo;
import com.localhost3030.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPatchTest extends TestBase {

    @Test
    public void patchProduct (){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Ultra Power Duracell - AA Batteries (8-Pack)");

        Response response = given ()
                .header("Content-Type","application/json")
                .pathParam("id", "9999699")
                .body(productPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
