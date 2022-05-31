/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit;

import com.csci5308.group7.logistics.CheckpointType;
import com.csci5308.group7.logistics.transit.interfaces.IState;

public class CheckedInState implements IState {
    @Override
    public void nextCheck(Cargo cargo) {
        cargo.setState(new CheckInBeltState());
    }

    @Override
    public void previousCheck(Cargo cargo) {
        System.out.println("This cargo is in initial state");
    }

    @Override
    public void printStatus() {
        System.out.println("This cargo is at the checkin counter");
    }

    @Override
    public CheckpointType getStatus() {
        return CheckpointType.CHECKIN_COUNTER;
    }
}
