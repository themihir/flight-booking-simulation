/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.database.DBConnection;
import com.csci5308.group7.database.IDBConnection;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportServices;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HelpAndSupportModel implements IHelpAndSupportModel {

    public static final String CUSTOMER_FEEDBACK = "Feedback";
    public static HelpAndSupportModel singleInstance = null;
    IHelpAndSupportServices iHelpAndSupportServices = new HelpAndSupportServices();

    IDBConnection dbConnection = DBConnection.instance();
    public static IHelpAndSupportModel instance() {
        if (null == singleInstance) {
            singleInstance = new HelpAndSupportModel();
        }
        return singleInstance;
    }

    @Override
    public HashMap<String, String> generateVoucher(IRequest voucherRequest){
        Connection connection = dbConnection.createConnection();
        HashMap<String, String> discountOffered = new HashMap<>();

        String userBookings = "SELECT bookingId FROM `5308_FlightBooking` WHERE userID = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userBookings);
            preparedStatement.setInt(1, voucherRequest.getUserId());
            ResultSet resultSet =  preparedStatement.executeQuery();
            int bookingsCounter = 0;
            while (resultSet.next()) {
                bookingsCounter = bookingsCounter+1;
            }
            discountOffered = iHelpAndSupportServices.calculateVoucherDiscount(bookingsCounter);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return discountOffered;
    }

    @Override
    public boolean makePendingPayment(IRequest paymentRequest) {
        Connection connection = dbConnection.createConnection();
        boolean paymentSuccess = false;
        String userAddress = "SELECT address FROM `users` WHERE userID = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAddress);
            preparedStatement.setInt(1, paymentRequest.getUserId());
            ResultSet resultSet =  preparedStatement.executeQuery();
            String address = null;
            while (resultSet.next()) {
                address = resultSet.getString("address");
            }
            int paymentProbability = iHelpAndSupportServices.calculatePaymentProbability(address);
            if (paymentProbability == 80 || paymentProbability == 90){
                paymentSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return paymentSuccess;
    }

    @Override
    public boolean feedbackSentimentAnalysis(IRequest feedbackRequest){
        Connection connection = dbConnection.createConnection();

        boolean feedbackSaved = false;
        PreparedStatement preparedStatement = null;
        try {
            String feedbackQuery = "INSERT INTO "+ CUSTOMER_FEEDBACK +"(feedback, feedbackClass) VALUES (?, ?)";
            String feedback = iHelpAndSupportServices.feedbackSentimentAnalysis(feedbackRequest.getFeedback());
            preparedStatement = connection.prepareStatement(feedbackQuery);
            preparedStatement.setString(1, feedbackRequest.getFeedback());
            preparedStatement.setString(2, feedback);
            preparedStatement.execute();
            feedbackSaved = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return feedbackSaved;
    }
}
