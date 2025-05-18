package com.thetestingacademy.tests.e2e_integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.request.Booking;
import com.thetestingacademy.pojos.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestIntegrationFlow1 extends BaseTest {
    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups = "qa", priority = 1)
    @Owner("Chiranjeevi")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = requestSpecification.when().body(payloadManager.createPayloadBookingAsString())
                .post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Chiranjeevi");
        assertActions.verifyIntegerKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
    }
    @Test(groups = "qa", priority = 2)
    @Owner("Chiranjeevi")
    @Description("TC#INT1 - Step 2. Verify the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        requestSpecification.basePath(basePathGET);
        response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getBookingFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertThat(booking.getFirstname()).isEqualTo("Chiranjeevi");

    }
    @Test(groups = "qa", priority = 3)
    @Owner("Chiranjeevi")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);


        String basePathPUT = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathPUT);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadManager.fullUpdatePayloadAsString());

        response = requestSpecification.when().put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getBookingFromJSON(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(),"Lucky");

    }
    @Test(groups = "qa", priority = 4)
    @Owner("Chiranjeevi")
    @Description("TC#INT1 - Step 4. Verify Partial Updated Booking by ID")
    public void testPartialUpdateBookingByID(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String)iTestContext.getAttribute("token");
        String basePathPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathPATCH);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadManager.partialUpdatePayloadAsString());

        response = requestSpecification.when().patch();

        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);
        Booking booking = payloadManager.getBookingFromJSON(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(),"Muppana");

    }
    @Test(groups = "qa", priority = 5)
    @Owner("Chiranjeevi")
    @Description("TC#INT1 - Step 5. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String)iTestContext.getAttribute("token");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        response = requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }
    }
