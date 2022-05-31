/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.abstractfactory;

import com.csci5308.group7.reschedule.interfaces.*;

public abstract class AbstractRescheduleFactory {
    private static AbstractRescheduleFactory instance = null;

    protected AbstractRescheduleFactory() {
    }

    public static AbstractRescheduleFactory instance() {
        if (instance == null) {
            instance = new RescheduleFactory();
        }
        return instance;
    }

    public abstract IRequest createRequest();
    public abstract IResponse createResponse();
    public abstract IReschedule createReschedule();
    public abstract IRescheduleModel createRescheduleService();

}
