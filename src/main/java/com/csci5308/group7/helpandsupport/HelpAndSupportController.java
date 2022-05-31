/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.abstractfactory.AbstractHelpAndSupportFactory;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupport;
import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportModel;
import com.csci5308.group7.helpandsupport.interfaces.IRequest;
import com.csci5308.group7.helpandsupport.interfaces.IResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/helpAndSupport")
public class HelpAndSupportController {

    AbstractHelpAndSupportFactory helpAndSupportFactory = AbstractHelpAndSupportFactory.instance();
    IHelpAndSupport iHelpAndSupport = helpAndSupportFactory.getVoucher();
    IHelpAndSupportModel iHelpAndSupportModel = helpAndSupportFactory.createHelpAndSupportModel();
    IRequest iRequest = helpAndSupportFactory.createRequest();
    IResponse iResponse = helpAndSupportFactory.createResponse();

    @GetMapping(value = "/voucher", produces = MediaType.APPLICATION_JSON_VALUE)
    public String voucher(@RequestHeader Map<String, String> headers) {
        int userId = Integer.parseInt(headers.get("userid"));
        iRequest.setUserId(userId);
        iHelpAndSupport.getVoucher(iRequest, iHelpAndSupportModel, iResponse);
        return iResponse.toString();
    }

    @GetMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public String payment(@RequestHeader Map<String, String> headers) {
        int userId = Integer.parseInt(headers.get("userid"));
        iRequest.setUserId(userId);
        iHelpAndSupport.makePendingPayment(iRequest, iHelpAndSupportModel, iResponse);
        return iResponse.toString();
    }

    @PostMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public String analyzeFeedback(@RequestBody String request) {
        try {
            JSONObject requestBody = new JSONObject(request);
            iRequest.setFeedback(requestBody.getString("feedback"));
            iHelpAndSupport.getFeedback(iRequest, iHelpAndSupportModel, iResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return iResponse.toString();
    }
}
