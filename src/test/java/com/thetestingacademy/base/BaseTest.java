package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

public class BaseTest {
    // CommonToAll Testcase
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;


    @BeforeTest
    public void setup() {
        System.out.println("Starting of the Test - Set Up");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL);
        requestSpecification.contentType(ContentType.JSON);


        /* both are same*/

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

    }
    public  String getToken(){
        requestSpecification.baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        // Setting the payload
        String payload = payloadManager.AuthPayloadasString();
        requestSpecification.contentType(ContentType.JSON).body(payload);
        // Get the Token
        response = requestSpecification.when().post();
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;

    }



    @AfterTest
    public void tearDown() {
        System.out.println("Finished the Test! - Tear Down");
    }

}
