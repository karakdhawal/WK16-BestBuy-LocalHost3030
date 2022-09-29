package com.localhost3030.stores;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class Assertion {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt (){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }
//Assert the following:
//1. Verify the if the total is equal to 1572
        @Test
    public void test001 (){ response.body("total", equalTo(1572)); }

//2. Verify the if the stores of limit is equal to 10
    @Test
    public void test002 (){ response.body("limit", equalTo(10));    }
// 3. Check the single ‘Name’ in the Array list (Lenovo)
    @Test
    public void test003 (){
        response.body("data.name", hasItem("Lenovo"));    }

//4. Check the multiple ‘Names’ in the ArrayList (Minnetonka, Maplewood, Northtown)
    @Test
    public void test004 (){response.body("data.name", hasItems("Minnetonka", "Maplewood", "Northtown")); }

//5. Verify the storeID(=10)?verify storeID field inside storeservices of the third store of second services
    @Test
    public void test005 (){ response.body("data[2].services[1].storeservices", hasKey("storeId"));}
//6. Check hash map values ‘createdAt’ inside storeservices map where store name = Maplewood
    @Test
    public void test006 (){response.body( "data.createdAt", hasItem("2016-11-17T17:57:04.414Z"));}
//7. Verify the state = MN of forth store
    @Test
    public void test007 (){response.body("data[3].state", equalTo("MN"));}
//8. Verify the store name = Rochester of 9th store
    @Test
    public void test008 (){ response.body("data[8].name", equalTo("Sioux Falls"));}
//9. Verify the storeId = 13 for the 6th store
    @Test
    public void test009 (){response.body("data[5].id", equalTo(13));}
//10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test010 (){response.body("data[6].services[3].id", equalTo(4));}
}
