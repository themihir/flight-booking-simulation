/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;


import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.factory.AbstractWebCheckinFactory;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckin;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/webcheckin")
public class WebCheckinController {
    public static final String ERROR_MESSAGE = "Something went wrong";
    AbstractWebCheckinFactory webCheckinFactory = AbstractWebCheckinFactory.instance();
    IWebCheckin webCheckin = webCheckinFactory.createWebCheckin();
    IWebCheckinModel iWebCheckinModel = webCheckinFactory.createWebCheckinModel();
    IRequest iRequest = webCheckinFactory.createRequest();
    IResponse iResponse = webCheckinFactory.createResponse();

    @RequestMapping(value = "/webcheckin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String webcheckin(@RequestBody String webCheckinRequest, @RequestHeader Map<String, String> headers){
        try{
            JSONObject requestBody = new JSONObject(webCheckinRequest);

            iRequest.setLastName(requestBody.getString("lastName"));
            iRequest.setPnr(requestBody.getString("pnr"));


            webCheckin.webCheckin(iRequest,iResponse,iWebCheckinModel);
            return iResponse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return iResponse.getFailureMessage(ERROR_MESSAGE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return iResponse.getFailureMessage(ERROR_MESSAGE);
        }

    }
    @RequestMapping(value = "/checkout",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String webcheckincheckout(@RequestBody String webCheckinRequest, @RequestHeader Map<String, String> headers){
        try {
            JSONObject requestBody = new JSONObject(webCheckinRequest);

            iRequest.setSeatNumber(requestBody.getString("seatnumber"));
            iRequest.setExtraBaggage(requestBody.getString("extrabaggage"));

            webCheckin.checkout(iRequest,iResponse,iWebCheckinModel);
            return iResponse.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return iResponse.getFailureMessage(ERROR_MESSAGE);
        }
    }
}
