/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit;

import com.csci5308.group7.logistics.CheckpointType;
import com.csci5308.group7.logistics.transit.interfaces.IState;

public class CarousalState implements IState {
    @Override
    public void nextCheck(Cargo cargo) {
        cargo.setState(new GateState());
    }

    @Override
    public void previousCheck(Cargo cargo) {
        cargo.setState(new CheckInBeltState());
    }

    @Override
    public void printStatus() {
        System.out.println("The cargo is in the baggage carousal");
    }

    @Override
    public CheckpointType getStatus() {
        return CheckpointType.BAGGAGE_CAROUSAL;
    }
}
