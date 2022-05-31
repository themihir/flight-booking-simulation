/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.interfaces.IResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements IResponse {
    private String message;
    private boolean success;
    private List<HashMap<String, Object>> counters;
    private List<Integer> baggageIds;
    private HashMap<String, Object> checkinCounter;
    private HashMap<String, Object> checkinBelt;
    private HashMap<String, Object> baggageCarousal;
    private HashMap<String, Object> gate;
    private String baggageStatus;

    public Response() {
        this.success = false;
        this.message = "Something went wrong";
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public List<HashMap<String, Object>> getCounters() {
        return counters;
    }

    @Override
    public void setCounters(List<HashMap<String, Object>> counters) {
        this.counters = counters;
    }

    @Override
    public List<Integer> getBaggageIds() {
        return baggageIds;
    }

    @Override
    public void setBaggageIds(List<Integer> baggageIds) {
        this.baggageIds = baggageIds;
    }

    @Override
    public HashMap<String, Object> getCheckinCounter() {
        return checkinCounter;
    }

    @Override
    public void setCheckinCounter(HashMap<String, Object> checkinCounter) {
        this.checkinCounter = checkinCounter;
    }

    @Override
    public HashMap<String, Object> getCheckinBelt() {
        return checkinBelt;
    }

    @Override
    public void setCheckinBelt(HashMap<String, Object> checkinBelt) {
        this.checkinBelt = checkinBelt;
    }

    @Override
    public HashMap<String, Object> getBaggageCarousal() {
        return baggageCarousal;
    }

    @Override
    public void setBaggageCarousal(HashMap<String, Object> baggageCarousal) {
        this.baggageCarousal = baggageCarousal;
    }

    @Override
    public HashMap<String, Object> getGate() {
        return gate;
    }

    @Override
    public void setGate(HashMap<String, Object> gate) {
        this.gate = gate;
    }

    @Override
    public String getBaggageStatus() {
        return baggageStatus;
    }

    @Override
    public void setBaggageStatus(String baggageStatus) {
        this.baggageStatus = baggageStatus;
    }

    @Override
    public String toString() {
        String responseData = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            responseData = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.message = null;
        this.success = false;
        this.counters = null;
        this.baggageIds = null;
        this.checkinCounter = null;
        this.checkinBelt = null;
        this.baggageCarousal = null;
        this.gate = null;
        this.baggageStatus = null;
        return responseData;
    }
}
