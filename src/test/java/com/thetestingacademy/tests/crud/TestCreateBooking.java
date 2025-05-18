package com.thetestingacademy.tests.crud;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @Owner("Chiranjeevi")
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBookingPOST_Positive() {

        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.body(payloadManager.createPayloadBookingAsString());
        response = requestSpecification.when().log().all().post();
        System.out.println(response.asString());

        // Extraction
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Verification Part
        assertActions.verifyIntegerKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Chiranjeevi");

    }
    @Test(groups = "reg", priority = 2)
    @Owner("Chiranjeevi")
    @Description("TC#2 - Verify that the Booking can't be Created, When Payload is null")
    public void testCreateBookingPOST_Negative() {
        System.out.println("I am Negative");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.body("");
        response = requestSpecification.when().log().all().post();
        System.out.println(response.asString());

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(500);
    }
    @Test(groups = "reg", priority = 3)
    @Owner("Chiranjeevi")
    @Description("TC#3 - Verify that the Booking can be Created, When Payload is CHINESE")
    public void testCreateBookingPOST_POSITIVE_CHINESE() {

        // Setup and Making a Request.
        System.out.println("I am Negative");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.body(payloadManager.createPayloadBookingAsStringNegative());
        response = requestSpecification.when().log().all().post();

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(500);

    }
    @Test(groups = "reg", priority = 1)
    @Owner("Chiranjeevi")
    @Description("TC#4 - Verify that the Booking can be Created using faker")
    public void testCreateBookingFaker() {

        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.body(payloadManager.createPayloadBookingFakerJS());
        response = requestSpecification.when().post();
        System.out.println(response.asString());
        validatableResponse=response.then().log().all();

        // Extraction
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Verification Part
        assertActions.verifyIntegerKeyNotNull(bookingResponse.getBookingid());
        validatableResponse.statusCode(200);

    }
}
