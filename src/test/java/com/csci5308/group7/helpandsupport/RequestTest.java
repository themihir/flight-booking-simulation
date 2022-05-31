/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.abstractfactory.AbstractHelpAndSupportFactoryMock;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class RequestTest {
    AbstractHelpAndSupportFactoryMock factoryMock = null;
    IRequest request = null;

    @BeforeEach
    void setUp() {
        factoryMock = AbstractHelpAndSupportFactoryMock.instance();
        request = factoryMock.createRequestMock();
    }

    @AfterEach
    void tearDown() {
        factoryMock = null;
        request = null;
    }
}
