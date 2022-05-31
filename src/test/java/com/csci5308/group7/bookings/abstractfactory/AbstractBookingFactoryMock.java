/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.bookings.abstractfactory;

import com.csci5308.group7.bookings.interfaces.*;

public abstract class AbstractBookingFactoryMock {

    private static AbstractBookingFactoryMock singleInstance = null;

    protected AbstractBookingFactoryMock(){
    }

    public static AbstractBookingFactoryMock instance (){
        if(singleInstance == null){
            return new BookingFactoryMock();
        }else {
            return singleInstance;
        }
    }

    public abstract IBooking getBookings();
    public abstract IManageBookingModel createBookingModel();
    public abstract IRequest createRequest();
    public abstract IResponse createResponse();
}
