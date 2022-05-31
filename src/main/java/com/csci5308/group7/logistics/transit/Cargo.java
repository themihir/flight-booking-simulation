/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit;

import com.csci5308.group7.logistics.transit.interfaces.*;

public class Cargo implements ICargo {
    private IState state = new CheckedInState();
    private int baggageId;

    public Cargo(int baggageId) {
        this.baggageId = baggageId;
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }

    @Override
    public IState getState() {
        return state;
    }

    @Override
    public void previousCheck() {
        state.previousCheck(this);
    }

    @Override
    public void nextCheck() {
        state.nextCheck(this);
    }

    @Override
    public void printStatus() {
        state.printStatus();
    }

    @Override
    public int getBaggageId() {
        return baggageId;
    }

    @Override
    public void setBaggageId(int baggageId) {
        this.baggageId = baggageId;
    }

    public void randomizeCheck() {
        int random = (int)(Math.random() * 4);
        if (random == 0) {
            state.nextCheck(this);
        } else if (random == 1) {
            state.nextCheck(this);
            state.nextCheck(this);
        } else if (random == 2) {
            state.nextCheck(this);
            state.nextCheck(this);
            state.nextCheck(this);
        }
    }
}
