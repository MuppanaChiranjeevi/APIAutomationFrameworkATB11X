package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHealthCheck extends BaseTest {

    @Test
    @Owner("Chiranjeevi")
    @Description("TC#2  - Verify Health")
    public  void testPositiveHealthCheck(){
        requestSpecification.basePath(APIConstants.PING_URL);
        response=requestSpecification.when().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
        assertActions.verifyResponseBody(response.asString(), "Created","Verify the token");


    }
    @Test
    @Owner("Chiranjeevi")
    @Description("TC#2  - Verify Health")
    public  void testNegativeHealthCheck(){
        requestSpecification.basePath(APIConstants.PING_URL);
        requestSpecification.body("{chiru}");
        response=requestSpecification.when().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(400);
        assertActions.verifyResponseBody(response.asString(), "Bad Request","Verify the token");


    }

}
