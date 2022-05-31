/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.interfaces;

import java.util.HashMap;
import java.util.List;

public interface IResponse {
    boolean getSuccess();

    void setSuccess(boolean success);

    String getMessage();

    void setMessage(String message);

    String toString();

    List<HashMap<String, Object>> getCounters();

    List<Integer> getBaggageIds();

    void setBaggageIds(List<Integer> baggageIds);

    void setCounters(List<HashMap<String, Object>> counters);

    HashMap<String, Object> getCheckinCounter();

    void setCheckinCounter(HashMap<String, Object> checkinCounter);

    HashMap<String, Object> getCheckinBelt();

    void setCheckinBelt(HashMap<String, Object> checkinBelt);

    HashMap<String, Object> getBaggageCarousal();

    void setBaggageCarousal(HashMap<String, Object> baggageCarousal);

    HashMap<String, Object> getGate();

    void setGate(HashMap<String, Object> gate);

    String getBaggageStatus();

    void setBaggageStatus(String baggageStatus);
}
