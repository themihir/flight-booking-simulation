/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.bookings.interfaces;

import java.util.HashMap;
import java.util.List;

public interface IResponse {
    public Boolean getSuccess();

    public void setSuccess(Boolean success);

    public List<HashMap <String, Object>> getResults();

    public void setResults(List<HashMap<String, Object>> results);

    public String toString();
}
