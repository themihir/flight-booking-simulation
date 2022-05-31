/**
 * @author Parth Shah
 */
package com.csci5308.group7.search.interfaces;

import java.util.HashMap;
import java.util.List;

public interface IResponse {
    Boolean getSuccess();

    void setSuccess(Boolean success);

    String toString();

    void appendResults(List<HashMap <String, Object>> results);

    void setResults(List<HashMap<String, Object>> searchResults);

    List<HashMap <String, Object>> getResults();

    void addResult(HashMap <String, Object> searchResult);

    String getFailureMessage(String message);
}
