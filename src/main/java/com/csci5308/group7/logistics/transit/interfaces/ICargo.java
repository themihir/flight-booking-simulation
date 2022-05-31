/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit.interfaces;

public interface ICargo {
    void setState(IState state);
    IState getState();
    void previousCheck();
    void nextCheck();
    void printStatus();
    int getBaggageId();
    void setBaggageId(int baggageId);
    void randomizeCheck();
}
