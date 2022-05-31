/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.abstractfactory;

import com.csci5308.group7.reschedule.interfaces.*;

public abstract class AbstractRescheduleFactoryMock {

    private static RescheduleFactoryMock singleInstance = null;

    protected AbstractRescheduleFactoryMock (){}

    public static RescheduleFactoryMock instance() {
        if (singleInstance == null) {
            singleInstance = new RescheduleFactoryMock();
        }
        return singleInstance;
    }

    public abstract IReschedule createReschedule();
    public abstract IRescheduleModel createRescheduleModel();
    public abstract IResponse createResponseMock();
    public abstract IRequest createRequest();
    public abstract IFacade createFacade();

}
