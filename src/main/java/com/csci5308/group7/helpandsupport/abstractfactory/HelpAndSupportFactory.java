/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.abstractfactory;

import com.csci5308.group7.helpandsupport.HelpAndSupport;
import com.csci5308.group7.helpandsupport.HelpAndSupportModel;
import com.csci5308.group7.helpandsupport.Request;
import com.csci5308.group7.helpandsupport.Response;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupport;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;

public class HelpAndSupportFactory extends AbstractHelpAndSupportFactory{

    @Override
    public IHelpAndSupport getVoucher(){
        return new HelpAndSupport();
    }

    @Override
    public IHelpAndSupportModel createHelpAndSupportModel(){
        return new HelpAndSupportModel();
    }

    @Override
    public IRequest createRequest(){
        return new Request();
    }

    @Override
    public IResponse createResponse(){
        return new Response();
    }
}
