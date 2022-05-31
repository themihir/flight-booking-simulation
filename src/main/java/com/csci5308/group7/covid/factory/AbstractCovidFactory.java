/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid.factory;

import com.csci5308.group7.covid.Interface.*;


public abstract class AbstractCovidFactory{
    private static AbstractCovidFactory instance = null;

    protected AbstractCovidFactory() {

    }

    public static AbstractCovidFactory instance() {
        if(instance == null) {
            instance = new CovidFactory();
        }

        return instance;

    }

    public abstract IRequest createRequest();

    public abstract ICovidModel createCovidModel();

    public abstract IResponse createResponse();

    public abstract ICovidModule createCovidMoules();

    public abstract ICovidInfectionStatus createCovidStatus();





}
