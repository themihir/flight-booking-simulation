/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.abstractfactory.AbstractHelpAndSupportFactoryMock;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HelpAndSupportModelMock implements IHelpAndSupportModel {

    AbstractHelpAndSupportFactoryMock factoryMock = null;
    IResponse response = null;
    IHelpAndSupportModel helpAndSupportModel = null;
    IRequest request = null;

    @BeforeEach
    void setUp() {
        factoryMock = AbstractHelpAndSupportFactoryMock.instance();
        response = factoryMock.createResponseMock();
        helpAndSupportModel = factoryMock.createHelpAndSupportModelMock();
        request = factoryMock.createRequestMock();
    }

    @AfterEach
    void tearDown() {
        factoryMock = null;
        response = null;
    }

    @Override
    public HashMap<String, String> generateVoucher(IRequest voucherRequest){
        HashMap<String, String> voucher = new HashMap<>();
        return voucher;
    }

    @Override
    public boolean makePendingPayment(IRequest voucherRequest){
        return true;
    }

    @Override
    public boolean feedbackSentimentAnalysis(IRequest feedback){
        return true;
    };

    @Test
    void testGenerateVoucher(){
        HashMap<String, String> voucher = new HashMap<>();
        request.setUserId(23);
        assertEquals(voucher.getClass().getName(), helpAndSupportModel.generateVoucher(request).getClass().getName());
    }

    @Test
    void testMakePendingPayment(){
        request.setUserId(32);
        assertTrue(helpAndSupportModel.makePendingPayment(request));
    }

    @Test
    void testMakePendingPaymentNegative(){
        request.setUserId(23);
        assertFalse(helpAndSupportModel.makePendingPayment(request));
    }

    @Test
    void testFeedbackSentimentAnalysis(){
        request.setFeedback("This is a good website ");
        assertTrue(helpAndSupportModel.feedbackSentimentAnalysis(request));
    }
}
