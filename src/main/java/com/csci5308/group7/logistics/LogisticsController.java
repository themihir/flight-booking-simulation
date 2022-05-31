/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactory;
import com.csci5308.group7.logistics.interfaces.ILogisticsModel;
import com.csci5308.group7.logistics.interfaces.ILogisticsService;
import com.csci5308.group7.logistics.interfaces.IResponse;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    private final AbstractLogisticsFactory logisticsFactory = AbstractLogisticsFactory.instance();
    private final ILogisticsService logisticsService = logisticsFactory.createLogisticsService();
    private final ILogisticsModel logisticsModel = logisticsFactory.createLogisticsModel();
    private final IResponse response = logisticsFactory.createResponse();

    @RequestMapping(value = "/getCheckinCounters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCheckinCounters() {
        try{
            logisticsService.getAllCheckinCounters(logisticsModel, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @RequestMapping(value = "/checkinBaggage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkinBaggage(@RequestBody String searchRequest, @RequestHeader Map<String, String> headers) {
        try{
            JSONObject requestBody = new JSONObject(searchRequest);
            int userId = Integer.parseInt(headers.get("userid"));
            requestBody.put("userId", userId);

            logisticsService.checkinBaggage(requestBody, logisticsModel, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @RequestMapping(value = "/calculateRoute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String calculateRoute(@RequestBody String searchRequest, @RequestHeader Map<String, String> headers) {
        try{
            JSONObject requestBody = new JSONObject(searchRequest);
            int userId = Integer.parseInt(headers.get("userid"));
            requestBody.put("userId", userId);

            logisticsService.calculateRoute(requestBody, logisticsModel, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @RequestMapping(value = "/registerTransition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerTransition(@RequestBody String searchRequest, @RequestHeader Map<String, String> headers) {
        try{
            JSONObject requestBody = new JSONObject(searchRequest);
            int userId = Integer.parseInt(headers.get("userid"));
            requestBody.put("userId", userId);

            logisticsService.registerTransition(requestBody, logisticsModel, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @RequestMapping(value = "/getBaggageState", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBaggageState(@RequestBody String searchRequest, @RequestHeader Map<String, String> headers) {
        try{
            JSONObject requestBody = new JSONObject(searchRequest);
            int userId = Integer.parseInt(headers.get("userid"));
            requestBody.put("userId", userId);

            logisticsService.getBaggageState(requestBody, logisticsModel, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
