/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.flightbook;

import com.csci5308.group7.flightbook.interfaces.IDetailsModel;
import com.csci5308.group7.flightbook.interfaces.IRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class FlightBookModelMock implements IDetailsModel {

    @Override
    public HashMap<String, Object> saveData(IRequest request) {

        HashMap<String, Object> bookResult = new HashMap<>();
        bookResult.put("bookingId", -1916939162);
        bookResult.put("userId", 1);
        bookResult.put("userType", 1);
        bookResult.put("firstName", "Mike");
        bookResult.put("lastName", "AAA");
        bookResult.put("airlines", "Atlantic Southeast Airlines");
        bookResult.put("airport", "Lehigh Valley International Airport");
        bookResult.put("flightNumber", "6619");
        bookResult.put("price", 30000);
        bookResult.put("extraBaggage", 1);
        bookResult.put("validProofID", "COX887165");
        bookResult.put("seatInformation", 55);
        bookResult.put("insurance", "Y");
        bookResult.put("origin", "Allentown");
        bookResult.put("destination", "Atlanta");
        bookResult.put("departureDate", "2015/01/01");
        bookResult.put("returnDate", "2021/01/01");
        bookResult.put("numberOfPassengers", 2);
        bookResult.put("flightClass", "ECONOMY");
        bookResult.put("flightType", "ROUNDTRIP");
        bookResult.put("pnr", "LQK7U9LB");
        bookResult.put("activeStatus", 1);

        return bookResult;
    }

    @Test
    void testCheckDetailModel(){
        assertEquals(DetailsModel.generateBookingId(), (int) System.currentTimeMillis()) ;
    }
}
