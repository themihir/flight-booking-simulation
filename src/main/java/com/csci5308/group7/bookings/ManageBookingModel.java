/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.bookings;

import com.csci5308.group7.bookings.interfaces.IManageBookingModel;
import com.csci5308.group7.bookings.interfaces.IRequest;
import com.csci5308.group7.database.DBConnection;
import com.csci5308.group7.database.IDBConnection;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ManageBookingModel implements IManageBookingModel {

    public static final String FLIGHT_BOOKING = "5308_FlightBooking";
    public static final int BASE_PRICE = 38250;
    public static final int EXTRA_BAGGAGE_PRICE = 2000;
    public static final int INSURANCE_PRICE = 1500;
    public static final int BUSINESS_CLASS_CHARGES = 20000;
    public static final String BUSINESS_CLASS = "BUSINESS";
    public static final String INSURANCE = "Y";
    public static ManageBookingModel singleInstance = null;

    IDBConnection dbConnection = DBConnection.instance();
    public static IManageBookingModel instance() {
        if (null == singleInstance) {
            singleInstance = new ManageBookingModel();
        }
        return singleInstance;
    }

    @Override
    public List<HashMap<String, Object>> bookings(IRequest bookingRequest) {
        Connection connection = dbConnection.createConnection();
        List<HashMap<String, Object>> results= new ArrayList<>();
        try {
            String getBookingQuery = "SELECT bookingId , userType,firstName, lastName, airlines, airport, " +
                    "flightNumber, price, extraBaggage, seatInformation, insurance, destination, departureDate, " +
                    "returnDate, numberOfPessengers, origin ,flightClass, flightType, pnr " +
                    "FROM `5308_FlightBooking` WHERE pnr = ? and userID = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(getBookingQuery);
            preparedStatement.setString(1, bookingRequest.getPNR());
            preparedStatement.setInt(2, bookingRequest.getUserId());
            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> result = new HashMap<String, Object>();
                result.put("bookingId", resultSet.getInt("bookingId"));
                result.put("userType", resultSet.getInt("userType"));
                result.put("firstName", resultSet.getString("firstName"));
                result.put("lastName", resultSet.getString("lastName"));
                result.put("airlines", resultSet.getString("airlines"));
                result.put("airport", resultSet.getString("airport"));
                result.put("flightNumber", resultSet.getString("flightNumber"));
                result.put("price", resultSet.getInt("price"));
                result.put("extraBaggage", resultSet.getInt("extraBaggage"));
                result.put("seatInformation", resultSet.getString("seatInformation"));
                result.put("insurance", resultSet.getString("insurance"));
                result.put("origin", resultSet.getString("origin"));
                result.put("destination", resultSet.getString("destination"));
                result.put("departureDate", resultSet.getString("departureDate"));
                result.put("returnDate", resultSet.getString("returnDate"));
                result.put("numberOfPessengers", resultSet.getInt("numberOfPessengers"));
                result.put("flightClass", resultSet.getString("flightClass"));
                result.put("flightType", resultSet.getString("flightType"));
                result.put("pnr", resultSet.getString("pnr"));
                results.add(result);
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
    public boolean editBooking(IRequest editRequest) {
        Connection connection = dbConnection.createConnection();
        boolean result = false;
        try {
            String editBookingQuery = "UPDATE "+ FLIGHT_BOOKING +" set `extraBaggage` = ?, `insurance` = ?, " +
                    "`flightClass` = ?, `numberOfPessengers` = ?, `seatInformation` = ?, `price` = ? where `pnr` = ?";

            JSONObject price = new JSONObject();
            try {
                price.put("baggage", editRequest.getExtraBaggage());
                price.put("insurance", editRequest.getInsurance());
                price.put("flightClass", editRequest.getFlightClass());
                price.put("noOfPassengers", editRequest.getNumberOfPassengers());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int updatedPrice = updatedPrice(price);
            PreparedStatement preparedStatement = connection.prepareStatement(editBookingQuery);
            preparedStatement.setInt(1, editRequest.getExtraBaggage());
            preparedStatement.setString(2, editRequest.getInsurance());
            preparedStatement.setString(3, editRequest.getFlightClass());
            preparedStatement.setInt(4, editRequest.getNumberOfPassengers());
            preparedStatement.setString(5, editRequest.getSeatInformation());
            preparedStatement.setInt(6, updatedPrice);
            preparedStatement.setString(7, editRequest.getPNR());

            int editResult =  preparedStatement.executeUpdate();
            if (editResult==1){
                result = true;
            }
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean deleteBooking(IRequest request){
        Connection connection = dbConnection.createConnection();
        boolean result = false;
        try {
            String deleteBookingQuery = "DELETE FROM "+ FLIGHT_BOOKING +" WHERE pnr = ? and userID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteBookingQuery);
            preparedStatement.setString(1, request.getPNR());
            preparedStatement.setInt(2, request.getUserId());
            int deleteResult =  preparedStatement.executeUpdate();
            if (deleteResult==1){
                result = true;
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
        return result;
    }

    public static int updatedPrice(JSONObject price) throws JSONException {
        int finalPrice = BASE_PRICE;
        if (price.getString("flightClass").toUpperCase(Locale.ROOT).equals(BUSINESS_CLASS)){
            finalPrice = finalPrice+BUSINESS_CLASS_CHARGES;
        }
        if (price.getString("insurance").toUpperCase(Locale.ROOT).equals(INSURANCE)){
            finalPrice = finalPrice+INSURANCE_PRICE;
        }
        finalPrice = (finalPrice*price.getInt("noOfPassengers"))+((price.getInt("baggage")-1)*EXTRA_BAGGAGE_PRICE);
        return finalPrice;
    }
}