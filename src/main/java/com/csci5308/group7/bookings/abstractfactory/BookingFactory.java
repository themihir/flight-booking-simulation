/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.bookings.abstractfactory;

import com.csci5308.group7.bookings.Booking;
import com.csci5308.group7.bookings.ManageBookingModel;
import com.csci5308.group7.bookings.Request;
import com.csci5308.group7.bookings.Response;
import com.csci5308.group7.bookings.interfaces.*;

public class BookingFactory extends AbstractBookingFactory {

    @Override
    public IBooking getBooking(){
        return new Booking();
    }

    @Override
    public IManageBookingModel createBookingModel(){
        return new ManageBookingModel();
    }

    @Override
    public IRequest createRequest(){
        return new Request();
    }

    @Override
    public IResponse createResponse(){
        return new Response();
    }

}
