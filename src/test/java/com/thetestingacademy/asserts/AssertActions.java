package com.thetestingacademy.asserts;

import io.restassured.response.Response;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {
    public void verifyResponseBody(String actual, String expected, String description){
        Assert.assertEquals(actual,expected,description);
    }
    public void verifyResponseBody(Integer actual, Integer expected, String description) {
        Assert.assertEquals(actual, expected, description);
    }
    public void verifyStatusCode(Response response, Integer expected) {
        Assert.assertEquals(response.getStatusCode(),expected);
    }
    public void verifyStringKey(String keyExpect,String keyActual){
        // AssertJ
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);

    }
    public void verifyIntegerKeyNotNull(Integer keyActual){
        // AssertJ
        assertThat(keyActual).isNotNull();
    }
    public void verifyStringKeyNotNull(String keyActual){
        // AssertJ
        assertThat(keyActual).isNotNull();
    }



}
