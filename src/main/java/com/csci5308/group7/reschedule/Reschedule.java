/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;

import com.csci5308.group7.reschedule.interfaces.IRequest;
import com.csci5308.group7.reschedule.interfaces.IReschedule;
import com.csci5308.group7.reschedule.interfaces.IRescheduleModel;
import com.csci5308.group7.reschedule.interfaces.IResponse;

import java.util.HashMap;
import java.util.List;

public class Reschedule implements IReschedule {

    @Override
    public void rescheduleFlight(IRequest request, IRescheduleModel rescheduleModel, IResponse response){

        List<HashMap<String, Object>> flights = rescheduleModel.getFlights(request);
        if(request.getFlightType().equals(FlightType.ROUNDTRIP)) {
            request.swapAirports();
            List<HashMap<String, Object>> returnFlights = rescheduleModel.getFlights(request);
            flights.addAll(returnFlights);
        }
        response.setResults(flights);
        response.setSuccess(true);
    }

    @Override
    public void updateRescheduledFlight(IRequest request, IRescheduleModel rescheduleModel, IResponse response){
        boolean isSuccess = rescheduleModel.updateFlightDetail(request);
        response.setSuccess(isSuccess);
    }
}
