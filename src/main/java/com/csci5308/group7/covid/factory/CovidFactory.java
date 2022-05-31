/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid.factory;

import com.csci5308.group7.covid.*;
import com.csci5308.group7.covid.Interface.*;


public class CovidFactory extends AbstractCovidFactory{


    @Override
    public IRequest createRequest() {
        return new Request();
    }

    @Override
    public ICovidModel createCovidModel() {
        return new CovidModel();
    }

    @Override
    public IResponse createResponse() {
        return new Response();
    }

    @Override
    public ICovidModule createCovidMoules() {
        return new CovidModule();
    }

    @Override
    public ICovidInfectionStatus createCovidStatus() {
        return new CovidInfectionStatusCalculator();
    }
}
