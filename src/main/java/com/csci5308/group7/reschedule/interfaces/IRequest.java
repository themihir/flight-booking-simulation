/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.interfaces;

import com.csci5308.group7.reschedule.FlightClass;
import com.csci5308.group7.reschedule.FlightType;


public interface IRequest {

    public int getUserId();

    public void setUserId(int userId);

    public String getAirlines();

    public void setAirlines(String airlines);

    public String getAirport();

    public void setAirport(String airport);

    public String getFlightNumber();

    public void setFlightNumber(String flightNumber);

    public String getOrigin();

    public void setOrigin(String origin);

    public String getDestination();

    public void setDestination(String destination);

    public String getDepartureDate();

    public void setDepartureDate(String departureDate);

    public String getReturnDate();

    public void setReturnDate(String returnDate);

    public FlightClass getFlightClass();

    public void setFlightClass(FlightClass flightClass);

    public FlightType getFlightType();

    public void setFlightType(FlightType flightType);

    public int parseDepartureDay();

    public int parseDepartureMonth();

    public int parseReturnDay();

    public int parseReturnMonth();

    public void swapAirports();

    public int getBookingId();

    public void setBookingId(int bookingId);

}
