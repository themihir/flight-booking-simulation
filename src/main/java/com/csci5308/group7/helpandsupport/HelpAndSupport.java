/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupport;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;

import java.util.HashMap;

public class HelpAndSupport implements IHelpAndSupport {
    public static final String SUCCESS_PAYMENT_RESPONSE = "Your payment is successful.. ";
    public static final String FAILED_PAYMENT_RESPONSE = "Payment Failed.. ";
    public static final String FEEDBACK_SUCCESS_RESPONSE = "Thank you for the valuable feedback ";
    public static final String FEEDBACK_FAILED_RESPONSE = "Failed to save feedback ";

    @Override
    public void getVoucher(IRequest request, IHelpAndSupportModel model, IResponse response){
        HashMap<String, String> voucher = model.generateVoucher(request);
        response.setResults(voucher);
        response.setSuccess(true);
    }

    @Override
    public void makePendingPayment(IRequest request, IHelpAndSupportModel model, IResponse response){
        boolean paymentStatus = model.makePendingPayment(request);
        HashMap<String, String> paymentResponse = new HashMap<>();
        if (paymentStatus){
            paymentResponse.put("Payment", SUCCESS_PAYMENT_RESPONSE);
        }else {
            paymentResponse.put("Payment", FAILED_PAYMENT_RESPONSE);
        }
        response.setResults(paymentResponse);
        response.setSuccess(true);
    }

    @Override
    public void getFeedback(IRequest request, IHelpAndSupportModel model, IResponse response){
        boolean feedbackStatus = model.feedbackSentimentAnalysis(request);
        HashMap<String, String> feedbackResponse = new HashMap<>();
        if (feedbackStatus){
            feedbackResponse.put("Feedback", FEEDBACK_SUCCESS_RESPONSE);
        }else {
            feedbackResponse.put("Feedback", FEEDBACK_FAILED_RESPONSE);
        }
        response.setResults(feedbackResponse);
        response.setSuccess(true);
    }
}
