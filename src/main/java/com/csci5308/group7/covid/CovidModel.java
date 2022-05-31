/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid;

import com.csci5308.group7.covid.Interface.ICovidInfectionStatus;
import com.csci5308.group7.covid.Interface.ICovidModel;
import com.csci5308.group7.covid.Interface.IRequest;
import com.csci5308.group7.covid.factory.AbstractCovidFactory;
import com.csci5308.group7.database.DBConnection;
import com.csci5308.group7.database.IDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class CovidModel implements ICovidModel {
    public static final String COVID_REPORTING = "Covid_Reporting";
    public static CovidModel singleInstance = null;
    IDBConnection dbConnection = DBConnection.instance();
    AbstractCovidFactory factory = AbstractCovidFactory.instance();
    ICovidInfectionStatus iCovidInfectionStatus = factory.createCovidStatus();

    public static ICovidModel instance(){
        if(singleInstance == null){
            singleInstance = new CovidModel();
        }
        return singleInstance;
    }

    @Override
    public HashMap<String, Object> postReport(IRequest covidSearch) {
        Connection connection = dbConnection.createConnection();
        HashMap<String,Object> covidRecord = new HashMap<String,Object>();
        try{
            String covidBookingQuery =  "INSERT INTO "+ COVID_REPORTING +"(identification, firstName, lastName, address, phoneNumber, bookingDate, infection_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement =  connection.prepareStatement(covidBookingQuery);
                preparedStatement.setString(1,covidSearch.getIdentification());
                preparedStatement.setString(2,covidSearch.getFirstName());
                preparedStatement.setString(3,covidSearch.getLastName());
                preparedStatement.setString(4,covidSearch.getAddress());
                preparedStatement.setString(5,covidSearch.getPhoneNumber());
                String currentDate = (java.time.LocalDate.now()).toString();
                preparedStatement.setString(6,currentDate);
                preparedStatement.setString(7,iCovidInfectionStatus.covidInfectionStatus());
                preparedStatement.executeUpdate();

                covidRecord.put("identification",covidSearch.getIdentification());
                covidRecord.put("firstName",covidSearch.getFirstName());
                covidRecord.put("lastName",covidSearch.getLastName());
                covidRecord.put("address",covidSearch.getAddress());
                covidRecord.put("phoneNumber",covidSearch.getPhoneNumber());
                covidRecord.put("bookingDate",currentDate);




            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return covidRecord;
    }
}
