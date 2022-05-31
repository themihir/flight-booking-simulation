/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.abstractfactory.AbstractHelpAndSupportFactoryMock;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportServices;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpAndSupportServiceTest {

    AbstractHelpAndSupportFactoryMock factoryMock = null;
    IResponse response = null;
    IHelpAndSupportModel helpAndSupportModel = null;
    IHelpAndSupportServices helpAndSupportServices = null;

    @BeforeEach
    void setUp() {
        factoryMock = AbstractHelpAndSupportFactoryMock.instance();
        response = factoryMock.createResponseMock();
        helpAndSupportModel = factoryMock.createHelpAndSupportModelMock();
        helpAndSupportServices = factoryMock.createHelpAndSupportServiceMock();
    }

    @AfterEach
    void tearDown() {
        factoryMock = null;
        response = null;
    }

    @Test
    void testCalculateVoucherDiscountZero(){
        HashMap<String, String> calculateVoucherDiscount = helpAndSupportServices.calculateVoucherDiscount(0);
        assertEquals("10%", calculateVoucherDiscount.get("discount"));
    }

    @Test
    void testCalculateVoucherDiscountOne(){
        HashMap<String, String> calculateVoucherDiscount = helpAndSupportServices.calculateVoucherDiscount(1);
        assertEquals("20%", calculateVoucherDiscount.get("discount"));
    }

    @Test
    void testCalculateVoucherDiscountTwo(){
        HashMap<String, String> calculateVoucherDiscount = helpAndSupportServices.calculateVoucherDiscount(2);
        assertEquals("30%", calculateVoucherDiscount.get("discount"));
    }

    @Test
    void testCalculateVoucherDiscountThree(){
        HashMap<String, String> calculateVoucherDiscount = helpAndSupportServices.calculateVoucherDiscount(3);
        assertEquals("40%", calculateVoucherDiscount.get("discount"));
    }

    @Test
    void testCalculateVoucherDiscountRandom(){
        HashMap<String, String> calculateVoucherDiscount = helpAndSupportServices.calculateVoucherDiscount(5);
        assertEquals("40%", calculateVoucherDiscount.get("discount"));
    }

    @Test
    void testCalculatePaymentProbabilitySouth(){
        int paymentProbability = helpAndSupportServices.calculatePaymentProbability("South Street");
        assertEquals(70, paymentProbability);
    }

    @Test
    void testCalculatePaymentProbability1234(){
        int paymentProbability = helpAndSupportServices.calculatePaymentProbability("1234 Street");
        assertEquals(70, paymentProbability);
    }

    @Test
    void testCalculatePaymentProbabilityFirstSecond(){
        int paymentProbability = helpAndSupportServices.calculatePaymentProbability("First Second Street");
        assertEquals(70, paymentProbability);
    }

    @Test
    void testFeedbackSentimentAnalysisPositive(){
        String feedbackSentimentAnalysis = helpAndSupportServices.feedbackSentimentAnalysis("This is very good, i like it");
        assertEquals("Positive Feedback", feedbackSentimentAnalysis);
    }

    @Test
    void testFeedbackSentimentAnalysisNegative(){
        String feedbackSentimentAnalysis = helpAndSupportServices.feedbackSentimentAnalysis("Very bad website");
        assertEquals("Negative Feedback", feedbackSentimentAnalysis);
    }

    @Test
    void testFeedbackSentimentAnalysisNeutral(){
        String feedbackSentimentAnalysis = helpAndSupportServices.feedbackSentimentAnalysis("this is good but bad");
        assertEquals("Neutral Feedback", feedbackSentimentAnalysis);
    }
}
