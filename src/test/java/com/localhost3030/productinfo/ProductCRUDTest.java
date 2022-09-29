package com.localhost3030.productinfo;

import com.localhost3030.model.ProductPojo;
import com.localhost3030.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {

    int id;
    @Test // list get
    public void test001 (){
        Response response = given ()
                .when()
                .get("");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // post id
    public void createProduct (){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("New Duracell - AA Batteries (8-Pack)");
        productPojo.setType("New HardGood");
        productPojo.setPrice( 8.49f);
        productPojo.setUpc("041333825014");
        productPojo.setShipping(1);
        productPojo.setDescription("New Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack");
        productPojo.setManufacturer("New Duracell");
        productPojo.setModel("MN1500B8Z");

        Response response = given()
                .header("Content-Type", "application/json") // get info from Postman Headers key and value
                .body(productPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
       id= response.body().path("id");
        System.out.println(id);

    }
    @Test //Patch
    public void patchProduct (){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Ultra Power Duracell - AA Batteries (8-Pack)");

        Response response = given()
                .header("Content-Type", "application/json")
              //  .pathParam("id", "9999702")
                .body(productPojo)
                .when()
                .patch(String.valueOf(id));
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test // delete
    public void test004 (){
        Response response = given()
               // .pathParam("id", "9999702")
                .when()
                .delete(String.valueOf(id));
        response.then().statusCode(204);
        response.prettyPrint();
    }
    @Test // delete id check
    public void test005 (){
        Response response = given()
               // .pathParam("id", "9999702")
                .when()
                .get(String.valueOf(id));
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
