/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.abstractfactory;

import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupport;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;

public abstract class AbstractHelpAndSupportFactory {
    private static AbstractHelpAndSupportFactory instance = null;

    protected AbstractHelpAndSupportFactory() {
    }

    public static AbstractHelpAndSupportFactory instance() {
        if (instance == null) {
            instance = new HelpAndSupportFactory();
        }
        return instance;
    }

    public abstract IHelpAndSupport getVoucher();
    public abstract IHelpAndSupportModel createHelpAndSupportModel();
    public abstract IRequest createRequest();
    public abstract IResponse createResponse();
}
