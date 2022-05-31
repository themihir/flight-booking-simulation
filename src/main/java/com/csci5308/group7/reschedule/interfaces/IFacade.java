/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.interfaces;

import com.csci5308.group7.reschedule.abstractfactory.AbstractRescheduleFactory;

public interface IFacade {

    public void facadeRescheduleFlight();

    public void facadeUpdateRescheduledFlight();

    public IRequest getRequest();

    public void setRequest(IRequest request);

    public IResponse getResponse();

    public void setResponse(IResponse response);

    public AbstractRescheduleFactory getRescheduleFactory();

    public void setRescheduleFactory(AbstractRescheduleFactory rescheduleFactory);

    public IRescheduleModel getRescheduleModel();

    public void setRescheduleModel(IRescheduleModel rescheduleModel);

    public IReschedule getReschedule();

    public void setReschedule(IReschedule reschedule);
}
