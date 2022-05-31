/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.abstractfactory;

import com.csci5308.group7.helpandsupport.*;
import com.csci5308.group7.helpandsupport.interfaces.*;

public class HelpAndSupportFactoryMock extends AbstractHelpAndSupportFactoryMock{

    @Override
    public IHelpAndSupport getVoucher(){
        return new HelpAndSupport();
    }

    @Override
    public IHelpAndSupportModel createHelpAndSupportModelMock(){
        return new HelpAndSupportModel();
    }

    @Override
    public IHelpAndSupportServices createHelpAndSupportServiceMock() {
        return new HelpAndSupportServices();
    }

    @Override
    public IRequest createRequestMock(){
        return new Request();
    }

    @Override
    public IResponse createResponseMock(){
        return new Response();
    }
}
