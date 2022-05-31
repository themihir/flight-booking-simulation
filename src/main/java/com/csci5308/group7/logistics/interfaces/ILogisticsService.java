/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.interfaces;

import org.json.JSONObject;

public interface ILogisticsService {
    void getAllCheckinCounters(ILogisticsModel logisticsModel, IResponse response);
    void checkinBaggage(JSONObject request, ILogisticsModel logisticsModel, IResponse response);
    void calculateRoute(JSONObject request, ILogisticsModel logisticsModel, IResponse response);
    void registerTransition (JSONObject request, ILogisticsModel logisticsModel, IResponse response);
    void getBaggageState (JSONObject request, ILogisticsModel logisticsModel, IResponse response);
}
