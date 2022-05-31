/**
 * @author Chandan Shukla
 */
package com.csci5308.group7.covid;

import com.csci5308.group7.covid.Interface.ICovidInfectionStatus;

import java.util.Random;


public class CovidInfectionStatusCalculator implements ICovidInfectionStatus {
public static Random rand = new Random();

    public String covidInfectionStatus(){

        boolean val = rand.nextInt(10)==0;

        return ((val)?"positive":"negative");
    }

}
