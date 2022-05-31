/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit;

import com.csci5308.group7.logistics.CheckpointType;
import com.csci5308.group7.logistics.transit.interfaces.IState;

public class GateState implements IState {
    @Override
    public void nextCheck(Cargo cargo) {
        System.out.println("The cargo is in the last state.");
    }

    @Override
    public void previousCheck(Cargo cargo) {
        cargo.setState(new CarousalState());
    }

    @Override
    public void printStatus() {
        System.out.println("The cargo is at the final gate.");
    }

    @Override
    public CheckpointType getStatus() {
        return CheckpointType.GATE;
    }
}
