package com.localhost3030.productinfo;

import com.localhost3030.model.ProductPojo;
import com.localhost3030.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPostTest extends TestBase {

    @Test
    public void createProduct(){
    ProductPojo productPojo = new ProductPojo();
    productPojo.setName("Ne Duracell - AA Batteries (8-Pack)");
    productPojo.setType("Ne HardGood");
    productPojo.setPrice( 8.49f);
    productPojo.setUpc("041333825014");
    productPojo.setShipping(1);
    productPojo.setDescription("Ne Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack");
    productPojo.setManufacturer("Duracell");
    productPojo.setModel("MN1500B8Z");

        Response response = given()
                .header("Content-Type", "application/json") // get info from Postman Headers key and value
                .body(productPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }
}
