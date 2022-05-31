/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;

import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckin;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;

import java.sql.SQLException;
import java.util.HashMap;

public class WebCheckin implements IWebCheckin {
    @Override
    public void webCheckin(IRequest webCheckinRequest, IResponse webCheckinResponse, IWebCheckinModel webCheckinModel) throws SQLException {
        HashMap<String, Object> webCheckingDetails = webCheckinModel.getRecord(webCheckinRequest);

        webCheckinResponse.setResult(webCheckingDetails);
        webCheckinResponse.setSuccess(true);

    }

    public void checkout(IRequest webCheckinRequest, IResponse webCheckinResponse, IWebCheckinModel webCheckinModel){
        String webPostDetails = webCheckinModel.postRecord(webCheckinRequest);
        webCheckinResponse.setPostResult(webPostDetails);
        webCheckinResponse.setSuccess(true);
    }
}
