/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.transit.interfaces;

import com.csci5308.group7.logistics.CheckpointType;
import com.csci5308.group7.logistics.transit.Cargo;

public interface IState {
    void nextCheck(Cargo cargo);
    void previousCheck(Cargo cargo);
    void printStatus();
    CheckpointType getStatus();
}
