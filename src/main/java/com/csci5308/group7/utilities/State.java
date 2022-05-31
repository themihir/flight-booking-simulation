/**
 * @author Parth Shah
 */
package com.csci5308.group7.utilities;

import com.csci5308.group7.utilities.interfaces.IState;

public class State implements IState {
    private String environment;
    private static IState singleInstance = null;
    private String[] args;

    public static IState getInstance() {
        if (singleInstance == null) {
            singleInstance = new State();
        }
        return singleInstance;
    }

    public State() {
        environment = "dev";
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String env) {
        environment = env;
    }

    @Override
    public String[] getArguments() {
        return args;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public String getEnvFileName() {
        String defaultProperty = "application.devint.properties";
        int index = -1;

        if (args == null){
            return defaultProperty;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-env")) {
                index = i + 1;
                break;
            }
        }
        if (index == -1){
            return defaultProperty;
        }
        switch (args[index]) {
            case "test":
                return "application.test.properties";
            case "prod":
                return "application.production.properties";
            default:
                return defaultProperty;
        }
    }
}
