/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.interfaces.ILogisticsModel;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogisticsModelMock implements ILogisticsModel {

    @Override
    public List<HashMap<String, Object>> getCheckpoints(String type) {
        List<HashMap<String, Object>> counterList = new ArrayList<>();

        HashMap<String, Object> counter = new HashMap<>();
        counter.put("id", 1);
        counter.put("xcoordinate", 45);
        counter.put("ycoordinate", 52);
        counter.put("operator", "Mark Cuban");
        counter.put("operatorContact", "1234567890");
        counter.put("publicIdentifier", "8973");
        counterList.add(counter);

        counter = new HashMap<>();
        counter.put("id", 2);
        counter.put("xcoordinate", 99);
        counter.put("ycoordinate", 12);
        counter.put("operator", "Jill Smith");
        counter.put("operatorContact", "1234567890");
        counter.put("publicIdentifier", "889773");
        counterList.add(counter);

        counter = new HashMap<>();
        counter.put("id", 3);
        counter.put("xcoordinate", 12);
        counter.put("ycoordinate", 87);
        counter.put("operator", "Andrew Smith");
        counter.put("operatorContact", "1234567890");
        counter.put("publicIdentifier", "12123");
        counterList.add(counter);

        return counterList;
    }

    @Override
    public List<Integer> checkinPassengerBaggage(JSONObject request) {
        List<Integer> baggageIds = new ArrayList<>();

        baggageIds.add(1);
        baggageIds.add(2);
        baggageIds.add(3);

        return baggageIds;
    }

    @Override
    public void updateBaggageStatus(CheckpointType type, int baggageId) {
    }

    @Override
    public CheckpointType getBaggageState(int baggageId) {
        if (baggageId == 1) {
            return CheckpointType.CHECKIN_COUNTER;
        } else if (baggageId == 2) {
            return CheckpointType.CHECKIN_BELT;
        } else {
            return CheckpointType.GATE;
        }
    }
}
