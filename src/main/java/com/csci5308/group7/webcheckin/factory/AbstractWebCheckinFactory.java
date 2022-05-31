/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.factory;


import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckin;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;

public abstract class AbstractWebCheckinFactory {
    private static AbstractWebCheckinFactory instance = null;

    protected AbstractWebCheckinFactory() {
    }

    public static AbstractWebCheckinFactory instance() {
        if (instance == null) {
            instance = new WebCheckinFactory();
        }
        return instance;
    }

    public abstract IRequest createRequest();

    public abstract IWebCheckinModel createWebCheckinModel();

    public abstract IWebCheckin createWebCheckin();

    public abstract IResponse createResponse();

}
