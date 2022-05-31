/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.bookings;

import com.csci5308.group7.bookings.abstractfactory.AbstractBookingFactoryMock;
import com.csci5308.group7.bookings.interfaces.IManageBookingModel;
import com.csci5308.group7.bookings.interfaces.IRequest;
import com.csci5308.group7.bookings.interfaces.IResponse;
import com.csci5308.group7.search.FlightClass;
import com.csci5308.group7.search.FlightType;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManageBookingModelMock implements IManageBookingModel {

    IManageBookingModel bookingModel = null;
    IRequest request = null;
    IResponse response = null;
    AbstractBookingFactoryMock bookingFactoryMock = null;

    @BeforeEach
    void setUp() {
        bookingFactoryMock = AbstractBookingFactoryMock.instance();
        response = bookingFactoryMock.createResponse();
        bookingModel = bookingFactoryMock.createBookingModel();
        request = bookingFactoryMock.createRequest();
    }

    @AfterEach
    void tearDown() {
        bookingFactoryMock = null;
        response = null;
    }

    @Override
    public List<HashMap<String, Object>> bookings(IRequest bookingRequest){
        List<HashMap<String, Object>> bookings = new ArrayList<>();
        HashMap<String, Object> result1 = new HashMap<String, Object>();
        result1.put("insurance", "Y");
        result1.put("lastName", "Sharma");
        result1.put("origin", "New York");
        result1.put("destination", "Atlanta");
        result1.put("numberOfPessengers", 1);
        result1.put("extraBaggage", 1);
        result1.put("flightClass", FlightClass.ECONOMY);
        result1.put("flightType", FlightType.ROUNDTRIP);
        result1.put("bookingId", -2043404026);
        result1.put("airport", "LaGuardia Airport (Marine Air Terminal)");
        result1.put("flightNumber", "904");
        result1.put("firstName", "Mukund");
        result1.put("seatInformation", "23");
        result1.put("returnDate", "2015-01-01");
        result1.put("pnr", "1U2MOVEF");
        result1.put("price", 38260);
        result1.put("airlines", "Delta Air Lines Inc.");
        result1.put("userType", 3);
        result1.put("departureDate", "2015-01-01");
        bookings.add(result1);
        return bookings;
    }

    @Override
    public boolean editBooking(IRequest bookingRequest){
        return true;
    }

    @Override
    public boolean deleteBooking(IRequest bookingRequest){
        return true;
    }

    @Test
    void testUpdatedPrice() {
        org.json.JSONObject priceValidator = new org.json.JSONObject();
        try {
            priceValidator.put("baggage", 2);
            priceValidator.put("insurance", "Y");
            priceValidator.put("flightClass", "BUSINESS");
            priceValidator.put("noOfPassengers", 2);
            assertEquals(121500, ManageBookingModel.updatedPrice(priceValidator));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEditBooking(){
        request.setExtraBaggage(2);
        request.setInsurance("Y");
        request.setFlightClass("BUSINESS");
        request.setNumberOfPassengers(3);
        request.setSeatInformation("12");
        boolean editResponse = bookingModel.editBooking(request);
        assertEquals(true, editResponse);
    }

    @Test
    void testDeleteBooking(){
        request.setPNR("ABCD1234");
        request.setUserId(98);
        boolean deleteResponse = bookingModel.deleteBooking(request);
        assertEquals(true, deleteResponse);
    }
}
