/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactoryMock;
import com.csci5308.group7.logistics.interfaces.IResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    AbstractLogisticsFactoryMock logisticsFactory = null;
    IResponse response = null;

    @BeforeEach
    void setUp() {
        logisticsFactory = AbstractLogisticsFactoryMock.instance();
        response = logisticsFactory.createResponseMock();
    }

    @AfterEach
    void tearDown() {
        logisticsFactory = null;
        response = null;
    }

    @Test
    void testToString() {
        assertEquals("{\"message\":\"Something went wrong\",\"success\":false}", response.toString());

        response.setMessage("Success");
        response.setSuccess(true);
        assertEquals("{\"message\":\"Success\",\"success\":true}", response.toString());

        response.setMessage("Something went wrong");
        response.setSuccess(false);
        assertEquals("{\"message\":\"Something went wrong\",\"success\":false}", response.toString());

        response.setMessage("Success");
        response.setSuccess(true);
        response.setBaggageStatus("CHECKIN_COUNTER");
        List<Integer> baggageIds = new ArrayList<Integer>();
        baggageIds.add(1);
        baggageIds.add(2);
        response.setBaggageIds(baggageIds);
        assertEquals("{\"message\":\"Success\",\"success\":true,\"baggageIds\":[1,2],\"baggageStatus\":\"CHECKIN_COUNTER\"}", response.toString());

        response.setMessage("Success");
        response.setSuccess(true);
        response.setBaggageStatus("GATE");
        HashMap<String, Object> gate = new HashMap<String, Object>();
        gate.put("gateId", "1");
        gate.put("gateName", "A5");
        response.setGate(gate);
        assertEquals("{\"message\":\"Success\",\"success\":true,\"gate\":{\"gateId\":\"1\",\"gateName\":\"A5\"},\"baggageStatus\":\"GATE\"}", response.toString());

        response.setMessage("Success");
        response.setSuccess(true);
        response.setBaggageStatus("CHECKIN_COUNTER");
        List<Integer> baggageIds1 = new ArrayList<Integer>();
        baggageIds1.add(1);
        baggageIds1.add(2);
        response.setBaggageIds(baggageIds1);
        HashMap<String, Object> gate1 = new HashMap<String, Object>();
        gate1.put("gateId", "1");
        gate1.put("gateName", "A5");
        response.setGate(gate1);
        assertEquals("{\"message\":\"Success\",\"success\":true,\"baggageIds\":[1,2],\"gate\":{\"gateId\":\"1\",\"gateName\":\"A5\"},\"baggageStatus\":\"CHECKIN_COUNTER\"}", response.toString());
    }
}