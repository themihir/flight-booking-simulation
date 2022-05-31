/**
 * @author Parth Shah
 */
package com.csci5308.group7.search.interfaces;

import com.csci5308.group7.search.FlightClass;
import com.csci5308.group7.search.FlightType;

public interface IRequest {
    String getOrigin();

    void setOrigin(String origin);

    String getDestination();

    void setDestination(String destination);

    String getDepartureDate();

    void setDepartureDate(String departureDate);

    String getReturnDate();

    void setReturnDate(String returnDate);

    int getNumberOfPassengers();

    void setNumberOfPassengers(int numberOfPassengers);

    FlightClass getFlightClass();

    void setFlightClass(FlightClass flightClass);

    FlightType getFlightType();

    void setFlightType(FlightType flightType);

    int parseDepartureDay();

    int parseDepartureMonth();

    int parseReturnDay();

    int parseReturnMonth();

    void swapAirports();

    int getCallingUserId();

    void setCallingUserId(int callingUserId);
}
