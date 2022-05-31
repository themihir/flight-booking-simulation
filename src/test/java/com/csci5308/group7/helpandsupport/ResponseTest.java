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

public class ResponseTest {

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
}
