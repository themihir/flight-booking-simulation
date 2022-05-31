/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.interfaces;


import java.util.HashMap;

public interface IHelpAndSupportServices {
    public HashMap<String, String> calculateVoucherDiscount(int bookingsCounter);
    public int calculatePaymentProbability(String address);
    public String feedbackSentimentAnalysis(String feedback);
}
