/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.abstractfactory.AbstractRescheduleFactoryMock;
import com.csci5308.group7.reschedule.interfaces.IRequest;
import com.csci5308.group7.reschedule.interfaces.IResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    AbstractRescheduleFactoryMock rescheduleFactoryMock = null;
    IResponse response = null;

    @BeforeEach
    void create(){
        rescheduleFactoryMock = AbstractRescheduleFactoryMock.instance();
        response = rescheduleFactoryMock.createResponseMock();
    }

    @AfterEach
    void destroy(){
        rescheduleFactoryMock = null;
        response = null;
    }

    @Test
    void testToString1(){
        assertEquals("{\"success\":false,\"results\":[]}", response.toString());
    }

    @Test
    void testToString2(){
        response.setSuccess(Boolean.TRUE);
        assertEquals("{\"success\":true,\"results\":[]}", response.toString());
    }

    @Test
    void testToString3(){

        List<HashMap<String, Object>> resultList = new ArrayList<>();
        HashMap<String, Object> record = new HashMap<String, Object>() {{
            put("one","two");
        }};
        resultList.add(record);
        response.setSuccess(Boolean.TRUE);
        response.setResults(resultList);
        String testExpectedOutput = "{\"success\":true,\"results\":[{\"one\":\"two\"}]}";
        assertEquals(testExpectedOutput, response.toString());
    }

    @Test
    void testToString4(){
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        HashMap<String, Object> record = new HashMap<String, Object>() {{
            put("three","four");
        }};
        resultList.add(record);
        response.setSuccess(Boolean.FALSE);
        response.setResults(resultList);
        String testExpectedOutput = "{\"success\":false,\"results\":[{\"three\":\"four\"}]}";
        assertEquals(testExpectedOutput, response.toString());
    }

    @Test
    void testResponse(){
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        HashMap<String, Object> record = new HashMap<String, Object>() {{
            put("three","four");
        }};
        resultList.add(record);

        response.setSuccess(Boolean.FALSE);
        assertEquals(Boolean.FALSE, response.getSuccess());
        response.setResults(resultList);
        assertEquals(resultList, response.getResults());
        response.setSuccess(Boolean.FALSE);
        assertEquals(Boolean.FALSE, response.getSuccess());


        String testExpectedOutput = "{\"success\":false,\"results\":[{\"three\":\"four\"}]}";
        assertEquals(testExpectedOutput, response.toString());
    }

    @Test
    void TestGetFailureMessage(){
        String testMessageInput = "This is test function";
        String testMessageOutput = "{  \"success\" : false, \"message\": " + testMessageInput + " }";
        assertEquals(testMessageOutput, response.getFailureMessage(testMessageInput));
    }

}
