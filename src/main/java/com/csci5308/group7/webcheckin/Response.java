/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;

import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Response implements IResponse {

    public boolean success;

    public HashMap<String, Object> result;

    public String postResult;

    public Response(){
        this.success = false;
        this.result = new HashMap<>();
    }

    @Override
    public Boolean getSuccess() {
        return success;
    }

    @Override
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public void setResult(HashMap<String, Object> searchResult) {
        this.result = searchResult;
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

    @Override
    public String getFailureMessage(String message) {
        return String.format("{  \"success\" : false, \"message\": %s }",message);
    }

    public void setPostResult(String postResult){
        this.postResult = postResult;
    }

}
