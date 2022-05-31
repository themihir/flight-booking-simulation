/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid;


import com.csci5308.group7.covid.Interface.ICovidModel;
import com.csci5308.group7.covid.Interface.ICovidModule;
import com.csci5308.group7.covid.Interface.IRequest;
import com.csci5308.group7.covid.Interface.IResponse;
import com.csci5308.group7.covid.factory.AbstractCovidFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/covidtest")
public class CovidController {
    public static final String ERROR_MESSAGE = "Something went wrong";
    public final AbstractCovidFactory covidFactory = AbstractCovidFactory.instance();
    ICovidModel iCovidModel = covidFactory.createCovidModel();
    IResponse iResponse = covidFactory.createResponse();
    IRequest iRequest = covidFactory.createRequest();
    ICovidModule iCovidModule = covidFactory.createCovidMoules();

    @RequestMapping(value = "/webcheckin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String covidReport(@RequestBody String covidReportRequest, @RequestHeader Map<String, String> headers){
        try{
            JSONObject requestBody = new JSONObject(covidReportRequest);

            iRequest.setLastName(requestBody.getString("lastName"));
            iRequest.setFirstName(requestBody.getString("firstName"));
            iRequest.setAddress(requestBody.getString("address"));
            iRequest.setIdentification(requestBody.getString("identification"));
            iRequest.setPhoneNumber(requestBody.getString("phoneNumber"));

            iCovidModule.covidModule(iRequest,iResponse,iCovidModel);
            return iResponse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return iResponse.getFailureMessage(ERROR_MESSAGE);
        }
    }

}
