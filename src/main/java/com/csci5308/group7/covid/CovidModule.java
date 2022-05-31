/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid;

import com.csci5308.group7.covid.Interface.ICovidModel;
import com.csci5308.group7.covid.Interface.ICovidModule;
import com.csci5308.group7.covid.Interface.IRequest;
import com.csci5308.group7.covid.Interface.IResponse;



import java.util.HashMap;

public class CovidModule implements ICovidModule {

    @Override
    public void covidModule(IRequest covidRequest, IResponse covidResponse, ICovidModel covidModel){
        HashMap<String, Object> covidDetails = covidModel.postReport(covidRequest);

        covidResponse.setResult(covidDetails);
        covidResponse.setSuccess(true);
    }
}
