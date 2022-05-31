/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.interfaces.IRequest;

public class Request implements IRequest {
    private int userId;
    private String feedback;

    public Request(){}
    public Request(int userId, String feedback){
        this.userId = userId;
        this.feedback = feedback;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getFeedback(){
        return feedback;
    }

    @Override
    public void setFeedback(String feedback){
        this.feedback = feedback;
    }
}
