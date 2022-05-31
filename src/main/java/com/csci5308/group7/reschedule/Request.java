/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.interfaces.IRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Request implements IRequest {

    private int userId;
    private String airlines;
    private String airport;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int bookingId;

    private FlightClass flightClass;
    private FlightType flightType;

    private final SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
    private Date date = null;
    String formattedDate = null;

    public Request(){
        //No parameter required
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public int parseDepartureDay() {
        try {
            date = parser.parse(this.getDepartureDate());
            SimpleDateFormat formatter = new SimpleDateFormat("dd");
            formattedDate = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(formattedDate);
    }

    @Override
    public int parseDepartureMonth() {
        try {
            date = parser.parse(this.getDepartureDate());
            SimpleDateFormat formatter = new SimpleDateFormat("MM");
            formattedDate = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(formattedDate);
    }

    @Override
    public int parseReturnDay() {
        try {
            date = parser.parse(this.getReturnDate());
            SimpleDateFormat formatter = new SimpleDateFormat("dd");
            formattedDate = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(formattedDate);
    }

    @Override
    public int parseReturnMonth() {

        try {
            date = parser.parse(this.getReturnDate());
            SimpleDateFormat formatter = new SimpleDateFormat("MM");
            formattedDate = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(formattedDate);
    }

    @Override
    public void swapAirports() {
        String temp = this.origin;
        this.origin = this.destination;
        this.destination = temp;
    }

}

