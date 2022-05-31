/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.abstractFactory;



import com.csci5308.group7.webcheckin.interfaces.IRequest;
import com.csci5308.group7.webcheckin.interfaces.IResponse;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckin;
import com.csci5308.group7.webcheckin.interfaces.IWebCheckinModel;

public abstract class AbstractWebCheckinFactoryMock {
    private static AbstractWebCheckinFactoryMock singleInstance = null;

    protected AbstractWebCheckinFactoryMock() {
    }

    public static AbstractWebCheckinFactoryMock instance (){
        if(singleInstance == null){
            return new WebCheckinFactoryMock();
        }else {
            return singleInstance;
        }
    }

    public abstract IWebCheckin createWebCheckin();
    public abstract IWebCheckinModel createWebCheckinModel();
    public abstract IRequest createRequest();
    public abstract IResponse createResponse();
}
