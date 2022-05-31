/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.interfaces;


import java.util.HashMap;

public interface IResponse {
    public Boolean getSuccess();

    public void setSuccess(Boolean success);

    public void setResults(HashMap<String, String> results);

    public String toString();
}
