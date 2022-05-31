/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.interfaces;

public interface IRequest {

    public String getLastName();

    public void setLastName(String lastName);

    public String getPnr();

    public void setPnr(String pnr);

    public String getSeatNumber();

    public void setSeatNumber(String seatNumber);

    public String getExtraBaggage();

    public void setExtraBaggage(String extraBaggage);


}
