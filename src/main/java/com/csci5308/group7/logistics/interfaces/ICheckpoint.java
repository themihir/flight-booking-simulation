/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.interfaces;

import java.util.HashMap;
import java.util.List;

public interface ICheckpoint {
    int getId();
    void setId(int id);
    String getType();
    void setType(String type);
    int getXcoordinate();
    void setXcoordinate(int xcoordinate);
    int getYcoordinate();
    void setYcoordinate(int ycoordinate);
    String getOperatorName();
    void setOperatorName(String operatorName);
    String getOperatorContact();
    void setOperatorContact(String operatorContact);
    String getPublicIdentifier();
    void setPublicIdentifier(String publicIdentifier);
}
