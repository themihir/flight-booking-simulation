/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactoryMock;
import com.csci5308.group7.logistics.interfaces.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogisticsServiceTest {

    AbstractLogisticsFactoryMock logisticsFactory = null;
    ILogisticsService logisticsService = null;
    ILogisticsModel logisticsModel = null;
    IResponse response = null;

    @BeforeEach
    void setUp() {
        logisticsFactory = AbstractLogisticsFactoryMock.instance();
        logisticsService = logisticsFactory.createLogisticsServiceMock();
        logisticsModel = logisticsFactory.createLogisticsModelMock();
        response = logisticsFactory.createResponseMock();
    }

    @AfterEach
    void tearDown() {
        logisticsFactory = null;
        logisticsService = null;
        logisticsModel = null;
        response = null;
    }

    @Test
    void getAllCheckinCounters() {
        logisticsService.getAllCheckinCounters(logisticsModel, response);
        assertEquals("{\"message\":\"Successfully retrieved all checkin counters\",\"success\":true,\"counters\":[{\"ycoordinate\":52,\"operatorContact\":\"1234567890\",\"id\":1,\"publicIdentifier\":\"8973\",\"operator\":\"Mark Cuban\",\"xcoordinate\":45},{\"ycoordinate\":12,\"operatorContact\":\"1234567890\",\"id\":2,\"publicIdentifier\":\"889773\",\"operator\":\"Jill Smith\",\"xcoordinate\":99},{\"ycoordinate\":87,\"operatorContact\":\"1234567890\",\"id\":3,\"publicIdentifier\":\"12123\",\"operator\":\"Andrew Smith\",\"xcoordinate\":12}]}", response.toString());
    }

    @Test
    void checkinBaggage() {
        try{
            JSONObject request = new JSONObject();
            request.put("baggageId", "67");
            logisticsService.checkinBaggage(request, logisticsModel, response);
            assertEquals("{\"message\":\"Successfully checked in baggage\",\"success\":true,\"baggageIds\":[1,2,3]}", response.toString());
        } catch(JSONException e){
            fail("JSONException");
        }
    }

    @Test
    void calculateRoute() {
        JSONObject request = new JSONObject();
        try{
            request.put("origin", "1");
            request.put("destination", "3");
            logisticsService.calculateRoute(request, logisticsModel, response);
            assertNotNull(response.toString());
        } catch(JSONException e){
            fail("JSONException");
        }
    }

    @Test
    void registerTransition() {
        JSONObject request = new JSONObject();
        try{
            request.put("baggageId", "67");
            request.put("transition", "GATE");
            logisticsService.registerTransition(request, logisticsModel, response);
            assertEquals("{\"message\":\"Successfully registered transition\",\"success\":true}", response.toString());
        } catch(JSONException e){
            fail("JSONException");
        }
    }

    @Test
    void getBaggageState() {
        JSONObject request = new JSONObject();
        try{
            request.put("baggageId", "67");
            logisticsService.getBaggageState(request, logisticsModel, response);
            assertEquals("{\"message\":\"Successfully retrieved baggage state\",\"success\":true,\"baggageStatus\":\"GATE\"}", response.toString());
        } catch(JSONException e){
            fail("JSONException");
        }
    }
}