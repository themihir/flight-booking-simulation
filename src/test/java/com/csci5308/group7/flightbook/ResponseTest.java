/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.flightbook;

import com.csci5308.group7.flightbook.interfaces.IRequest;
import com.csci5308.group7.flightbook.factory.AbstractFlightBookFactoryMock;
import com.csci5308.group7.flightbook.interfaces.IDetailsModel;
import com.csci5308.group7.flightbook.interfaces.IResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {
    AbstractFlightBookFactoryMock flightBookFactoryMock;
    IRequest request = null;
    IResponse response = null;
    IDetailsModel flightModel = null;

    String TEST_RESPONSE = "{\"success\":true,\"result\":{\"insurance\":\"Y\",\"lastName\":\"AAA\",\"origin\":" +
            "\"Allentown\",\"destination\":\"Atlanta\",\"extraBaggage\":1,\"flightClass\":\"ECONOMY\",\"flightType\":" +
            "\"ROUNDTRIP\",\"userId\":1,\"bookingId\":-1916939162,\"airport\":\"Lehigh Valley International Airport\"," +
            "\"flightNumber\":\"6619\",\"firstName\":\"Mike\",\"seatInformation\":55,\"returnDate\":\"2021/01/01\"," +
            "\"validProofID\":\"COX887165\",\"activeStatus\":1,\"pnr\":\"LQK7U9LB\",\"price\":30000," +
            "\"airlines\":\"Atlantic Southeast Airlines\",\"numberOfPassengers\":2,\"userType\":1," +
            "\"departureDate\":\"2015/01/01\"}}";


    @BeforeEach
    void create(){
        flightBookFactoryMock = AbstractFlightBookFactoryMock.instance();
        request = flightBookFactoryMock.createRequest();
        response = flightBookFactoryMock.createResponse();
        flightModel = flightBookFactoryMock.createFlightBookModel();
    }

    @AfterEach
    void destroy(){
        flightBookFactoryMock = null;
        response = null;
    }

    @Test
    void testToString() {
        HashMap<String, Object> result = flightModel.saveData(request);
        assertEquals(22, result.size());

        response.setSuccess(true);
        response.setResult(result);
        assertEquals(TEST_RESPONSE, response.toString());
    }

    @Test
    void testGetResult(){
        response.setResult(flightModel.saveData(request));
        HashMap<String, Object> result = response.getResult();
        assertEquals(1, result.get("activeStatus"));
        assertEquals(1, result.get("extraBaggage"));
        assertEquals("ECONOMY", result.get("flightClass"));
    }


}
