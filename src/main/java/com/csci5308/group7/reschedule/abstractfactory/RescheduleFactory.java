/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.abstractfactory;

import com.csci5308.group7.reschedule.Request;
import com.csci5308.group7.reschedule.Reschedule;
import com.csci5308.group7.reschedule.RescheduleModel;
import com.csci5308.group7.reschedule.Response;
import com.csci5308.group7.reschedule.interfaces.IRequest;
import com.csci5308.group7.reschedule.interfaces.IReschedule;
import com.csci5308.group7.reschedule.interfaces.IRescheduleModel;
import com.csci5308.group7.reschedule.interfaces.IResponse;


public class RescheduleFactory extends AbstractRescheduleFactory {

    @Override
    public IRequest createRequest() {
        return new Request();
    }

    @Override
    public IResponse createResponse() {
        return new Response();
    }

    @Override
    public IReschedule createReschedule() {
        return new Reschedule();
    }

    @Override
    public IRescheduleModel createRescheduleService() {
        return new RescheduleModel();
    }
}
