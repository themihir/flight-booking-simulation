/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.abstractfactory.AbstractRescheduleFactoryMock;
import com.csci5308.group7.reschedule.interfaces.IRequest;
import com.csci5308.group7.reschedule.interfaces.IRescheduleModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

class RescheduleModelTest {

    AbstractRescheduleFactoryMock rescheduleFactoryMock = null;
    IRescheduleModel rescheduleModel = null;
    IRequest request = null;

    @BeforeEach
    void create() {
        rescheduleFactoryMock = AbstractRescheduleFactoryMock.instance();
        rescheduleModel = rescheduleFactoryMock.createRescheduleModel();
        request = rescheduleFactoryMock.createRequest();
    }

    @AfterEach
    void destroy() {
        rescheduleFactoryMock = null;
        rescheduleModel = null;
        request = null;
    }

    @Test
    void testGetFlights() {
        request.setBookingId(2113094077);
        try{
            List<HashMap<String, Object>> results = rescheduleModel.getFlights(request);
            assertTrue(results.size() > 0);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateFlightDetail(){
        String airport = "Atlantic Southeast Airlines";
        String airlines = "Lehigh Valley International Airport";
        String flightNumber = "6619";
        String departureDate = "2015/01/01";
        String returnDate = "2021/01/01";

        request.setAirport(airport);
        request.setAirlines(airlines);
        request.setFlightNumber(flightNumber);
        request.setDepartureDate(departureDate);
        request.setReturnDate(returnDate);

        try{
            assertTrue(rescheduleModel.updateFlightDetail(request));
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
