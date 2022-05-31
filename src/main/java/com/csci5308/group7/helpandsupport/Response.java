/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.interfaces.IResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Response implements IResponse {
    public Boolean success;
    public HashMap<String, String> results;

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

    public void setResults(HashMap<String, String> results) {
        this.results = results;
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
