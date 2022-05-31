/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.abstractfactory.AbstractRescheduleFactory;
import com.csci5308.group7.reschedule.interfaces.*;

public class FacadeClass implements IFacade {

    private AbstractRescheduleFactory rescheduleFactory = AbstractRescheduleFactory.instance();
    private IRescheduleModel rescheduleModel = rescheduleFactory.createRescheduleService();
    private IReschedule reschedule = rescheduleFactory.createReschedule();
    private IResponse response = rescheduleFactory.createResponse();
    private IRequest request = rescheduleFactory.createRequest();

    @Override
    public void facadeRescheduleFlight(){
        reschedule.rescheduleFlight(request, rescheduleModel, response);
    }

    @Override
    public void facadeUpdateRescheduledFlight(){
        reschedule.updateRescheduledFlight(request, rescheduleModel, response);
    }

    @Override
    public IRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(IRequest request) {
        this.request = request;
    }

    @Override
    public IResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(IResponse response) {
        this.response = response;
    }

    @Override
    public AbstractRescheduleFactory getRescheduleFactory() {
        return rescheduleFactory;
    }

    @Override
    public void setRescheduleFactory(AbstractRescheduleFactory rescheduleFactory) {
        this.rescheduleFactory = rescheduleFactory;
    }

    @Override
    public IRescheduleModel getRescheduleModel() {
        return rescheduleModel;
    }

    @Override
    public void setRescheduleModel(IRescheduleModel rescheduleModel) {
        this.rescheduleModel = rescheduleModel;
    }

    @Override
    public IReschedule getReschedule() {
        return reschedule;
    }

    @Override
    public void setReschedule(IReschedule reschedule) {
        this.reschedule = reschedule;
    }
}
