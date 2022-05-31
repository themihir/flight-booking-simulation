/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.flightbook;

import com.csci5308.group7.flightbook.factory.AbstractFlightBookFactoryMock;
import com.csci5308.group7.flightbook.interfaces.IRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    AbstractFlightBookFactoryMock flightBookFactoryMock = null;
    IRequest request = null;

    String TEST_REQUEST = "{\"userId\": 1, \"userType\": 1, \"firstName\": \"Mike\", \"lastName\": \"AAA\", " +
            "\"airlines\": \"Atlantic Southeast Airlines\", \"airport\": \"Lehigh Valley International Airport\", " +
            "\"flightNumber\": \"6619\", \"price\": 200, \"extraBaggage\": 1, \"validProofID\": \"COX887165\", " +
            "\"seatInformation\": 55, \"insurance\": \"YES\", \"origin\": \"Allentown\", \"destination\": \"Atlanta\", " +
            "\"departureDate\": \"2015/01/01\", \"returnDate\": \"2021/01/01\", \"numberOfPassengers\": 2, " +
            "\"flightClass\": \"ECONOMY\", \"flightType\": \"ROUNDTRIP\" }";

    @BeforeEach
    void create(){
        flightBookFactoryMock = AbstractFlightBookFactoryMock.instance();
        request = flightBookFactoryMock.createRequest();
    }

    @AfterEach
    void destroy(){
        flightBookFactoryMock = null;
        request = null;
    }

    @Test
    void testRequest() throws JSONException {
        JSONObject requestBody = new JSONObject(TEST_REQUEST);
        request.setLastName(requestBody.getString("lastName"));
        request.setUserId(requestBody.getInt("userId"));
        request.setFlightType(requestBody.getString("flightType"));
        assertEquals(requestBody.getString("lastName"), request.getLastName());
        assertEquals(requestBody.getInt("userId"), request.getUserId());
    }
}
