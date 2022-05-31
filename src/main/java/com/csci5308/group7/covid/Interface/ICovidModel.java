/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid.Interface;

import java.util.HashMap;



public interface ICovidModel {

    public HashMap<String, Object> postReport(IRequest covidSearch);
}
