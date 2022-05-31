/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactory;
import com.csci5308.group7.logistics.interfaces.*;

import com.csci5308.group7.logistics.transit.interfaces.ICargo;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;

public class LogisticsService implements ILogisticsService {

    private final AbstractLogisticsFactory logisticsFactory = AbstractLogisticsFactory.instance();

    @Override
    public void getAllCheckinCounters(ILogisticsModel logisticsModel, IResponse response) {

        List<HashMap<String, Object>> checkinCounters = logisticsModel.getCheckpoints(CheckpointType.CHECKIN_COUNTER.toString());
        response.setSuccess(true);
        response.setCounters(checkinCounters);
        response.setMessage("Successfully retrieved all checkin counters");
    }

    @Override
    public void checkinBaggage(JSONObject request, ILogisticsModel logisticsModel, IResponse response) {

        response.setSuccess(false);
        response.setMessage("Failed to check in baggage");

        List<Integer> baggageIds = logisticsModel.checkinPassengerBaggage(request);

        if (baggageIds.size() > 0) {
            response.setBaggageIds(baggageIds);
            response.setSuccess(true);
            response.setMessage("Successfully checked in baggage");
        }
    }

    @Override
    public void calculateRoute(JSONObject request, ILogisticsModel logisticsModel, IResponse response) {

        try {
            List<HashMap<String, Object>> checkinCounters = logisticsModel.getCheckpoints(CheckpointType.CHECKIN_COUNTER.toString());
            List<HashMap<String, Object>> checkinBelts = logisticsModel.getCheckpoints(CheckpointType.CHECKIN_BELT.toString());
            List<HashMap<String, Object>> baggageCarousals = logisticsModel.getCheckpoints(CheckpointType.BAGGAGE_CAROUSAL.toString());
            List<HashMap<String, Object>> gates = logisticsModel.getCheckpoints(CheckpointType.GATE.toString());

            HashMap<String, Object> sourceCheckinCounter = Checkpoint.getRandomCheckpoint(checkinCounters);
            int sourceCheckinCounterX = (int) sourceCheckinCounter.get("xcoordinate");
            int sourceCheckinCounterY = (int) sourceCheckinCounter.get("ycoordinate");

            HashMap<String, Object> nearestCheckinBelt = null;
            double nearestCheckinBeltDistance = Integer.MAX_VALUE;
            IEdge edge1 = null;
            for (HashMap<String, Object> checkinBelt : checkinBelts) {
                int checkinBeltX = (int) checkinBelt.get("xcoordinate");
                int checkinBeltY = (int) checkinBelt.get("ycoordinate");
                edge1 = logisticsFactory.createEdge(sourceCheckinCounterX, sourceCheckinCounterY, checkinBeltX, checkinBeltY);

                if(edge1.getDistance() < nearestCheckinBeltDistance) {
                    nearestCheckinBelt = checkinBelt;
                    nearestCheckinBeltDistance = edge1.getDistance();
                }
            }

            int sourceCheckinBeltX = (int) nearestCheckinBelt.get("xcoordinate");
            int sourceCheckinBeltY = (int) nearestCheckinBelt.get("ycoordinate");

            HashMap<String, Object> nearestBaggageCarousal = null;
            double nearestBaggageCarousalDistance = Integer.MAX_VALUE;
            IEdge edge2 = null;
            for (HashMap<String, Object> baggageCarousal : baggageCarousals) {
                int baggageCarousalX = (int) baggageCarousal.get("xcoordinate");
                int baggageCarousalY = (int) baggageCarousal.get("ycoordinate");
                edge2 = logisticsFactory.createEdge(sourceCheckinBeltX, sourceCheckinBeltY, baggageCarousalX, baggageCarousalY);

                if(edge2.getDistance() < nearestBaggageCarousalDistance) {
                    nearestBaggageCarousal = baggageCarousal;
                    nearestBaggageCarousalDistance = edge2.getDistance();
                }
            }

            int nearestBaggageCarousalX = (int) nearestBaggageCarousal.get("xcoordinate");
            int nearestBaggageCarousalY = (int) nearestBaggageCarousal.get("ycoordinate");

            HashMap<String, Object> destinationGate = Checkpoint.getRandomCheckpoint(gates);
            int destinationGateX = (int) destinationGate.get("xcoordinate");
            int destinationGateY = (int) destinationGate.get("ycoordinate");

            IEdge edge3 = logisticsFactory.createEdge(nearestBaggageCarousalX, nearestBaggageCarousalY, destinationGateX, destinationGateY);

            sourceCheckinCounter.put("distance", edge1.getDistance());
            nearestCheckinBelt.put("distance", edge2.getDistance());
            nearestBaggageCarousal.put("distance", edge3.getDistance());

            response.setCheckinCounter(sourceCheckinCounter);
            response.setCheckinBelt(nearestCheckinBelt);
            response.setBaggageCarousal(nearestBaggageCarousal);
            response.setGate(destinationGate);
            response.setSuccess(true);
            response.setMessage("Successfully calculated route");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Failed to calculate route");
            e.printStackTrace();
        }
    }

    @Override
    public void registerTransition (JSONObject request, ILogisticsModel logisticsModel, IResponse response) {

        try {
            int baggageId = request.getInt("baggageId");
            ICargo cargo = logisticsFactory.createCargo(baggageId);
            cargo.randomizeCheck();

            CheckpointType currentState = CheckpointType.valueOf(request.getString("transition"));
            logisticsModel.updateBaggageStatus(currentState, baggageId);

            response.setSuccess(true);
            response.setMessage("Successfully registered transition");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Failed to register transition");
            e.printStackTrace();
        }
    }

    @Override
    public void getBaggageState(JSONObject request, ILogisticsModel logisticsModel, IResponse response) {

        try{
            int baggageId = request.getInt("baggageId");
            CheckpointType currentState = logisticsModel.getBaggageState(baggageId);

            response.setSuccess(true);
            response.setBaggageStatus(currentState.toString());
            response.setMessage("Successfully retrieved baggage state");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Failed to retrieve baggage state");
            e.printStackTrace();
        }
    }
}
