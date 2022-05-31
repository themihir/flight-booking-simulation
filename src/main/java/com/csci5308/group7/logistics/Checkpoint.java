/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.interfaces.ICheckpoint;

import java.util.HashMap;
import java.util.List;

public class Checkpoint implements ICheckpoint {
    private int id;
    private String type;
    private int xcoordinate;
    private int ycoordinate;
    private String operatorName;
    private String operatorContact;
    private String publicIdentifier;

    public Checkpoint() {
    }

    public static HashMap<String, Object> getRandomCheckpoint(List<HashMap<String, Object>> list) {
        if(list.size() > 0) {
            int random = (int) (Math.random() * list.size());
            System.out.println(random);
            return list.get(random);
        } else {
            return null;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getXcoordinate() {
        return xcoordinate;
    }

    @Override
    public void setXcoordinate(int xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    @Override
    public int getYcoordinate() {
        return ycoordinate;
    }

    @Override
    public void setYcoordinate(int ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    @Override
    public String getOperatorName() {
        return operatorName;
    }

    @Override
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String getOperatorContact() {
        return operatorContact;
    }

    @Override
    public void setOperatorContact(String operatorContact) {
        this.operatorContact = operatorContact;
    }

    @Override
    public String getPublicIdentifier() {
        return publicIdentifier;
    }

    @Override
    public void setPublicIdentifier(String publicIdentifier) {
        this.publicIdentifier = publicIdentifier;
    }
}
