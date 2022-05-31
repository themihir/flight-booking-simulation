/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.covid.Interface;

public interface IRequest {

    public String getLastName();

    public void setLastName(String lastName);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getAddress();

    public void setAddress(String address);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public String getIdentification();

    public void setIdentification(String identification);
}
