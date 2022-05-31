/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.database.DBConnection;
import com.csci5308.group7.database.IDBConnection;
import com.csci5308.group7.reschedule.interfaces.IRequest;
import com.csci5308.group7.reschedule.interfaces.IRescheduleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RescheduleModel implements IRescheduleModel {

    private static RescheduleModel singleInstance = null;
    IDBConnection dbConnection = DBConnection.instance();

    public static IRescheduleModel instance() {
        if (singleInstance == null){
            singleInstance = new RescheduleModel();
        }
        return singleInstance;
    }

    @Override
    public List<HashMap<String, Object>> getFlights(IRequest request){
        Connection connection = dbConnection.createConnection();
        List<HashMap<String, Object>> results= new ArrayList<>();

        try {
            String originAirportCode = null;
            String destinationAirportCode = null;
            String origin = null;
            String destination = null;
            String flightClass = null;
            String flightType = null;

            String fetchBookingRow = "SELECT * FROM `5308_FlightBooking` WHERE bookingId = ?";
            PreparedStatement fetchPlacesStatement = connection.prepareStatement(fetchBookingRow);
            fetchPlacesStatement.setInt(1, request.getBookingId());
            ResultSet placeQueryResult =  fetchPlacesStatement.executeQuery();

            while (placeQueryResult.next()) {
                origin = placeQueryResult.getString("origin");
                destination = placeQueryResult.getString("destination");
                flightClass = placeQueryResult.getString("FlightClass");
                flightType = placeQueryResult.getString("flightType");
                request.setOrigin(origin);
                request.setDestination(destination);
                request.setFlightType(FlightType.valueOf(placeQueryResult.getString("flightType")));
                request.setFlightClass(FlightClass.valueOf(placeQueryResult.getString("flightClass")));
            }

            String airportCodeQuery = "SELECT airport_id, city FROM `5308_Airports` WHERE city = ? OR city = ?";

            PreparedStatement airportCodeStatement = connection.prepareStatement(airportCodeQuery);
            airportCodeStatement.setString(1, origin);
            airportCodeStatement.setString(2, destination);
            ResultSet airportCodeResult =  airportCodeStatement.executeQuery();

            while (airportCodeResult.next()) {
                if(airportCodeResult.getString("city").equals(origin)) {
                    originAirportCode = airportCodeResult.getString("airport_id");
                }

                if(airportCodeResult.getString("city").equals(destination)) {
                    destinationAirportCode = airportCodeResult.getString("airport_id");
                }
            }

            String flightToQuery = "SELECT * FROM `5308_FlightSchedule` a LEFT JOIN `5308_Airlines` b ON a.`AIRLINE` = b.airlineId LEFT JOIN `5308_Airports` c ON a.`ORIGIN_AIRPORT` = c.airport_id WHERE ORIGIN_AIRPORT = ? AND DESTINATION_AIRPORT = ?";
            PreparedStatement flightToStatement = connection.prepareStatement(flightToQuery);
            flightToStatement.setString(1, originAirportCode);
            flightToStatement.setString(2, destinationAirportCode);

            ResultSet flightToResult = flightToStatement.executeQuery();

            while (flightToResult.next()) {
                HashMap<String, Object> searchResult = new HashMap<>();
                int year = flightToResult.getInt("YEAR");
                int month = flightToResult.getInt("MONTH");
                int day = flightToResult.getInt("DAY");
                searchResult.put("id", flightToResult.getInt("id"));
                searchResult.put("airlines", flightToResult.getString("name"));
                searchResult.put("airport", flightToResult.getString("airport"));
                searchResult.put("flightNumber", flightToResult.getString("FLIGHT_NUMBER"));
                searchResult.put("flightDuration", flightToResult.getInt("AIR_TIME"));
                searchResult.put("departureTime", flightToResult.getInt("DEPARTURE_TIME"));
                searchResult.put("arrivalTime", flightToResult.getInt("SCHEDULED_ARRIVAL"));
                searchResult.put("departureDate", LocalDate.of(year, month, day).toString());
                searchResult.put("arrivalDate", LocalDate.of(year, month, day).toString());
                searchResult.put("facilities", null);
                searchResult.put("flightType", flightType);
                searchResult.put("flightClass", flightClass);
                searchResult.put("flightCapacity", 150);
                searchResult.put("economyClass", 0);
                searchResult.put("businessClass", 0);
                searchResult.put("firstClass", 0);
                results.add(searchResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @Override
    public boolean updateFlightDetail(IRequest request){
        Connection connection = dbConnection.createConnection();
        boolean isSuccess = Boolean.FALSE;
        try{

            String updateQuery = "UPDATE `5308_FlightBooking` SET airlines=?, airport=?, flightNumber=?, departureDate=?, " +
                    "returnDate=? WHERE bookingId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, request.getAirlines());
            preparedStatement.setString(2, request.getAirport());
            preparedStatement.setString(3, request.getFlightNumber());
            preparedStatement.setString(4, request.getDepartureDate());
            preparedStatement.setString(5, request.getReturnDate());
            preparedStatement.setInt(6, request.getBookingId());
            preparedStatement.execute();

            isSuccess = Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                isSuccess = Boolean.FALSE;
            }
        }
        return isSuccess;
    }



}
