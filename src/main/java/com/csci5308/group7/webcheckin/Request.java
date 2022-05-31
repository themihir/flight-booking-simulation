/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin;

import com.csci5308.group7.webcheckin.interfaces.IRequest;

public class Request implements IRequest {

    private String lastName;
    private String pnr;
    private String seatNumber;
    private String extraBaggage;


    public Request() {
    }

    public Request(String lastName,String pnr){
        this.lastName = lastName;
        this.pnr = pnr;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPnr() {
        return pnr;
    }

    @Override
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @Override
    public String getSeatNumber() {
        return seatNumber;
    }

    @Override
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String getExtraBaggage() {
        return extraBaggage;
    }

    @Override
    public void setExtraBaggage(String extraBaggage) {
        this.extraBaggage = extraBaggage;
    }


}
