/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.interfaces;

import java.util.HashMap;

public interface IResponse {
    public Boolean getSuccess();

    public void setSuccess(Boolean success);

    public String toString();

    public void setResult(HashMap<String,Object> searchResult);

    public String getFailureMessage(String message);

    public void setPostResult(String postResult);

}
