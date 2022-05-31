/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.reschedule;


import com.csci5308.group7.reschedule.interfaces.IFacade;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/reschedule")
public class RescheduleController {


    private IFacade facade = new FacadeClass();

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reschedule(@RequestBody String rescheduleRequest, @RequestHeader Map<String, String> headers){
        JSONObject requestBody = null;
        try {
            requestBody = new JSONObject(rescheduleRequest);

            facade.getRequest().setBookingId(requestBody.getInt("bookingId"));
            facade.facadeRescheduleFlight();
            return facade.getResponse().toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return facade.getResponse().getFailureMessage("Something went wrong");
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateReschedule(@RequestBody String updateRequest, @RequestHeader Map<String, String> headers){

        JSONObject requestBody = null;
        try {
            requestBody = new JSONObject(updateRequest);

            facade.getRequest().setAirlines(requestBody.getString("airlines"));
            facade.getRequest().setAirport(requestBody.getString("airport"));
            facade.getRequest().setFlightNumber(requestBody.getString("flightNumber"));
            facade.getRequest().setDepartureDate(requestBody.getString("departureDate"));
            facade.getRequest().setReturnDate(requestBody.getString("returnDate"));

            facade.facadeUpdateRescheduledFlight();

            return facade.getResponse().getSuccess().toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return facade.getResponse().getFailureMessage("Something went wrong");
        }
    }
}
