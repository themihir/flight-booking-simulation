/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.interfaces;

import com.csci5308.group7.logistics.CheckpointType;
import org.json.JSONObject;

import java.util.List;
import java.util.HashMap;

public interface ILogisticsModel {
    List<HashMap<String, Object>> getCheckpoints(String type);
    List<Integer> checkinPassengerBaggage(JSONObject request);
    void updateBaggageStatus (CheckpointType type, int baggageId);
    CheckpointType getBaggageState (int baggageId);
}
