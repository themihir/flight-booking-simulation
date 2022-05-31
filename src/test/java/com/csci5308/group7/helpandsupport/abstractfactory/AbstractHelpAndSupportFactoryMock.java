/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.abstractfactory;

import com.csci5308.group7.helpandsupport.interfaces.*;

public abstract class AbstractHelpAndSupportFactoryMock {
    private static AbstractHelpAndSupportFactoryMock singleInstance = null;

    protected AbstractHelpAndSupportFactoryMock() {
    }

    public static AbstractHelpAndSupportFactoryMock instance (){
        if(singleInstance == null){
            return new HelpAndSupportFactoryMock();
        }else {
            return singleInstance;
        }
    }

    public abstract IHelpAndSupport getVoucher();
    public abstract IHelpAndSupportModel createHelpAndSupportModelMock();
    public abstract IHelpAndSupportServices createHelpAndSupportServiceMock();
    public abstract IRequest createRequestMock();
    public abstract IResponse createResponseMock();
}
