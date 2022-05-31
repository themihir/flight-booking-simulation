/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;

import com.csci5308.group7.webcheckin.abstractFactory.AbstractWebCheckinFactoryMock;
import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    AbstractWebCheckinFactoryMock webCheckinFactoryMock = null;
    IResponse response = null;
    IWebCheckinModel webCheckinModel = null;
    IRequest request = null;

    String TEST_RESPONSE = "{\"success\":true,\"result\":{},\"postResult\":null}";

    @BeforeEach
    void setUp() {
        webCheckinFactoryMock = AbstractWebCheckinFactoryMock.instance();
        response = webCheckinFactoryMock.createResponse();
        webCheckinModel = webCheckinFactoryMock.createWebCheckinModel();
        request = webCheckinFactoryMock.createRequest();
    }

    @BeforeEach
    void tearDown() {
        webCheckinFactoryMock = null;
        response = null;
    }

    @Test
    void testToString() {
        HashMap<String, Object> results = null;
        try {
            results = webCheckinModel.getRecord(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(0, results.size());

        response.setResult(results);
        response.setSuccess(true);

        assertEquals(TEST_RESPONSE, response.toString());
    }


}