/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit;

import com.csci5308.group7.logistics.CheckpointType;
import com.csci5308.group7.logistics.transit.interfaces.IState;

public class CheckInBeltState implements IState {
    @Override
    public void nextCheck(Cargo cargo) {
        cargo.setState(new CarousalState());
    }

    @Override
    public void previousCheck(Cargo cargo) {
        cargo.setState(new CheckedInState());
    }

    @Override
    public void printStatus() {
        System.out.println("The cargo is on the check-in belt.");
    }

    @Override
    public CheckpointType getStatus() {
        return CheckpointType.CHECKIN_BELT;
    }
}
