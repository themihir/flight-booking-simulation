/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.abstractfactory;

import com.csci5308.group7.reschedule.FacadeClass;
import com.csci5308.group7.reschedule.Request;
import com.csci5308.group7.reschedule.RescheduleModel;
import com.csci5308.group7.reschedule.Response;
import com.csci5308.group7.reschedule.interfaces.*;

public class RescheduleFactoryMock extends AbstractRescheduleFactoryMock{

    @Override
    public IReschedule createReschedule() {
        return null;
    }

    @Override
    public IRescheduleModel createRescheduleModel() {
        return new RescheduleModel();
    }

    @Override
    public IResponse createResponseMock() {
        return new Response();
    }

    @Override
    public IRequest createRequest() {
        return new Request();
    }

    @Override
    public IFacade createFacade() {
        return new FacadeClass();
    }
}
