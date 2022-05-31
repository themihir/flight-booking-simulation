/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.interfaces;

public interface IReschedule {
    public void rescheduleFlight(IRequest request, IRescheduleModel rescheduleService, IResponse response);
    public void updateRescheduledFlight(IRequest request, IRescheduleModel rescheduleModel, IResponse response);
}
