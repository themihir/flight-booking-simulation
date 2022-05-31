/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule.interfaces;

import java.util.HashMap;
import java.util.List;

public interface IResponse {

    public String getFailureMessage(String message);

    public Boolean getSuccess();

    public void setSuccess(Boolean success);

    public List<HashMap<String, Object>> getResults();

    public void setResults(List<HashMap<String, Object>> results);

    public String toString();

}
