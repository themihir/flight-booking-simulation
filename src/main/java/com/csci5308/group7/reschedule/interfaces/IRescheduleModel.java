/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.interfaces;


import java.util.HashMap;
import java.util.List;

public interface IRescheduleModel {
    public List<HashMap<String, Object>> getFlights(IRequest request);
    public boolean updateFlightDetail(IRequest request);
}

