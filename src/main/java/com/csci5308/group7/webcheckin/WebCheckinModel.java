/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;

import com.csci5308.group7.database.DBConnection;
import com.csci5308.group7.database.IDBConnection;
import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;



public class WebCheckinModel implements IWebCheckinModel {
    public static final String FLIGHT_BOOKING = "5308_FlightBooking";

    public static WebCheckinModel singleInstance = null;
    IDBConnection dbConnection = DBConnection.instance();

    public static IWebCheckinModel instance() {
        if(singleInstance == null){
            singleInstance = new WebCheckinModel();
        }
        return singleInstance;
    }


    @Override
    public HashMap<String,Object> getRecord(IRequest checkinRequest) throws SQLException {

        Connection connection = dbConnection.createConnection();
        HashMap<String,Object> webcheckinRecord = new HashMap<String,Object>();
        try{

            String webcheckinQuery = "SELECT * FROM "+FLIGHT_BOOKING+ " as a WHERE pnr = ? AND lastName = ?";
            PreparedStatement webcheckinStatement = connection.prepareStatement(webcheckinQuery);
            webcheckinStatement.setString(1, checkinRequest.getPnr());
            webcheckinStatement.setString(2, checkinRequest.getLastName());
            try{

                ResultSet webcheckinDetail = webcheckinStatement.executeQuery();
                try{


                    while(webcheckinDetail.next()) {

                        webcheckinRecord.put("userType", webcheckinDetail.getInt("userType"));
                        webcheckinRecord.put("firstName", webcheckinDetail.getString("firstName"));
                        webcheckinRecord.put("lastName", webcheckinDetail.getString("lastName"));
                        webcheckinRecord.put("flightNumber", webcheckinDetail.getString("flightNumber"));
                        webcheckinRecord.put("extraBaggage", webcheckinDetail.getInt("extraBaggage"));
                        webcheckinRecord.put("insurance", webcheckinDetail.getString("insurance"));
                        webcheckinRecord.put("origin", webcheckinDetail.getString("origin"));
                        webcheckinRecord.put("destination", webcheckinDetail.getString("destination"));
                        webcheckinRecord.put("departureDate", webcheckinDetail.getString("departureDate"));
                        webcheckinRecord.put("pnr", webcheckinDetail.getString("pnr"));
                        webcheckinRecord.put("activeStatus", (webcheckinDetail.getInt("activeStatus")==1)?"Active":"Cancelled");
                        webcheckinRecord.put("seatinformation",webcheckinDetail.getString("seatinformation"));
                    }
                }finally {
                    webcheckinDetail.close();
                }
            }finally{
                webcheckinStatement.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return webcheckinRecord;
    }

    @Override
    public String postRecord(IRequest postRequest) {
        Connection connection = dbConnection.createConnection();
        HashMap<String,Object> postRecord = new HashMap<String,Object>();
        int  result = 0;
        PreparedStatement preparedStatement = null;
        try{
            String postQuery = "UPDATE "+FLIGHT_BOOKING+" SET seatInformation = ?, extraBaggage = ? where pnr = ?;";
            preparedStatement = connection.prepareStatement(postQuery);
            preparedStatement.setString(1, postRequest.getSeatNumber());
            preparedStatement.setString(2, postRequest.getExtraBaggage());
            preparedStatement.setString(3, postRequest.getPnr());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(result == 1){
            return "Success";
        }else{
            return "Failed";
        }
    }
}
