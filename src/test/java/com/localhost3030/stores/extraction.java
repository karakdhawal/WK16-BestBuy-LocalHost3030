package com.localhost3030.stores;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class extraction {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //Extraction Example
    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002 (){
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th store
    @Test
    public void test003 (){
        String fifthStoreName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the fifth store is : " + fifthStoreName);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Store Names are : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005 (){
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the size of the data list
    @Test
    public void test006 (){
        List<Integer> dataSize = response.extract().path("data");
        int sizes = dataSize.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + sizes);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007 (){
        List<HashMap<String,?>>values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for store name 'St Cloud' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){
        List<String> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of store name 'Rochester' is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store
    @Test
    public void test009(){
        //List<HashMap<String,?>> services = response.extract().path("data.findAll{it.name=='Oakdale'}.services");
        List<HashMap<String,?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of the 8th store is : " + services);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010 (){
        List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services[5].name=='Windows Store'}.storeservices");
//         List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services=='Windows Store'}.storeservices");
//        List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services.name=='Windows Store'}.storeservices");
        // List<HashMap<String,?>> storeServices = response.extract().path("data.services.name=='Windows Store'");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services of the store where service name 'Window Store' are : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> storeID = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store ID of all the stores are : " +storeID);
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test012 (){
        List<Integer> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ID of all the stores are : " +id);
        System.out.println("------------------End of Test---------------------------");

    }
    //13. Find the store names Where state = MN
    @Test
    public void test013 () {
        List<String>storename = response.extract().path("data.findAll{it.state=='MN'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  store names where state name MN are : " +storename);
        System.out.println("------------------End of Test---------------------------");
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014 (){
        List<List<String>> servicesAtRochester = response.extract().path("data.findAll{it.name=='Rochester'}.services.name");
        int noOfServices = servicesAtRochester.get(0).size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The no of service where store name is Rochester : " + noOfServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015 (){
        List<List<?>> createdAt = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for the all service whose name is Windows Store : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Minnetonka”
    @Test
        public void test016(){
        List<HashMap<String,?>> services = response.extract().path("data.findAll{it.name=='Minnetonka'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for the store name Minnetonka is : " + services);
        System.out.println("------------------End of Test---------------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void test017 (){
        List <Integer> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip for all store are : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Minnetonka
    @Test
    public void test018 (){
        List<?> zip = response.extract().path("data.findAll{it.name=='Minnetonka'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip for Minnetonka : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater

    //20. Find the lat of all the stores
    @Test
            public void test020() {
        List<?> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat for all stores are : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}
