/**
 * @author Mihir Sanchaniya
 */
package com.csci5308.group7.flightbook.factory;

import com.csci5308.group7.flightbook.interfaces.*;

public abstract class AbstractFlightBookFactoryMock {

    private static AbstractFlightBookFactoryMock singleInstance = null;

    protected AbstractFlightBookFactoryMock (){}

    public static AbstractFlightBookFactoryMock instance(){
        if (singleInstance == null){
            return new FlightBookFactoryMock();
        }
        return singleInstance;
    }

    public abstract IBook createFlightBook();
    public abstract IDetailsModel createFlightBookModel();
    public abstract IRequest createRequest();
    public abstract IResponse createResponse();
}
