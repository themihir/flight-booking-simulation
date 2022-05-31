/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid.Interface;

import java.util.HashMap;

public interface IResponse {

    public Boolean getSuccess();

    public void setSuccess(Boolean success);

    public void setResult(HashMap<String, Object> searchResults);

    public HashMap <String, Object> getResult();

    public String getFailureMessage(String message);

    public String toString();



}
