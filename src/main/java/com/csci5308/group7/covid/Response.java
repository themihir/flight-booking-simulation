/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid;

import com.csci5308.group7.covid.Interface.IResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.HashMap;


public class Response implements IResponse {
    public Boolean success;
    public HashMap<String, Object> results;

    public Response() {
        this.success = false;
        this.results = new HashMap<>();
    }
    public Response(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setResult(HashMap <String, Object> searchResults) {
        this.results = searchResults;
    }

    public HashMap <String, Object> getResult() {
        return results;
    }

    @Override
    public String getFailureMessage(String message) {
        return "{  \"success\" : false, \"message\": " + message + " }";
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

        return responseData;
    }

}
