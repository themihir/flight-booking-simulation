/**
 * @author Parth Shah
 */
package com.csci5308.group7.utilities.interfaces;

public interface IState {
    String getEnvironment();
    void setEnvironment(String env);
    String[] getArguments();
    void setArguments(String[] args);
    String getEnvFileName();
}
