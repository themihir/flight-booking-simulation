/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.factory;

import com.csci5308.group7.webcheckin.Request;
import com.csci5308.group7.webcheckin.Response;
import com.csci5308.group7.webcheckin.WebCheckin;
import com.csci5308.group7.webcheckin.WebCheckinModel;
import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckin;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;

public class WebCheckinFactory extends AbstractWebCheckinFactory{

    @Override
    public IRequest createRequest() {
        return new Request();
    }

    @Override
    public IWebCheckinModel createWebCheckinModel() {
        return new WebCheckinModel();
    }

    @Override
    public IWebCheckin createWebCheckin() {
        return new WebCheckin();
    }

    @Override
    public IResponse createResponse() {
        return new Response();
    }
}
