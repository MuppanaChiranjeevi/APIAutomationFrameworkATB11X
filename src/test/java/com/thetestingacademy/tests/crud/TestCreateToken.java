package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Chiranjeevi")
    @Description("TC#1  - Create Token and Verify")
    public void testToken(){
        // Prep of Req
        requestSpecification.basePath(APIConstants.AUTH_URL);
        requestSpecification.body(payloadManager.AuthPayloadasString());

        // Making of the Request.
        response = requestSpecification.when().log().all().post();

        // Extraction ( JSON String response to Java Object
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);

        // Validation of the request.
        assertActions.verifyStringKeyNotNull(token);

    }

}
