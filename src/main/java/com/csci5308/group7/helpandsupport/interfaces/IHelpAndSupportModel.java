/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.interfaces;


import java.util.HashMap;

public interface IHelpAndSupportModel {
    public HashMap<String, String> generateVoucher(IRequest voucherRequest);
    public boolean makePendingPayment(IRequest voucherRequest);
    public boolean feedbackSentimentAnalysis(IRequest feedback);
}