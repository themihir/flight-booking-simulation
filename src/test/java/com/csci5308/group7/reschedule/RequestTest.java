/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.abstractfactory.AbstractRescheduleFactoryMock;
import com.csci5308.group7.reschedule.interfaces.IRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    AbstractRescheduleFactoryMock rescheduleFactoryMock = null;
    IRequest request = null;

    @BeforeEach
    void create(){
        rescheduleFactoryMock = AbstractRescheduleFactoryMock.instance();
        request = rescheduleFactoryMock.createRequest();
    }

    @AfterEach
    void destroy(){
        rescheduleFactoryMock = null;
        request = null;
    }

    @Test
    void testSwapAirport(){
        String origin = "origin";
        String destination = "destination";
        request.setOrigin(origin);
        request.setDestination(destination);

        request.swapAirports();
        assertEquals(destination, request.getOrigin());
        assertEquals(origin, request.getDestination());
    }

    @Test
    void TestParseReturnMonth(){
        request.setReturnDate("2015/01/01");
        assertEquals(1, request.parseReturnMonth());
    }

    @Test
    void TestParseReturnDay(){
        request.setReturnDate("2015/01/01");
        assertEquals(1, request.parseReturnDay());
    }

    @Test
    void TestParseDepartureDay(){
        request.setDepartureDate("2015/01/01");
        assertEquals(1, request.parseDepartureDay());
    }

    @Test
    void TestParseDepartureMonth(){
        request.setDepartureDate("2015/01/01");
        assertEquals(1, request.parseDepartureMonth());
    }
}
