/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.abstractFactory;

import com.csci5308.group7.logistics.interfaces.*;
import com.csci5308.group7.logistics.transit.interfaces.ICargo;

public abstract class AbstractLogisticsFactory {
    private static AbstractLogisticsFactory instance = null;

    protected AbstractLogisticsFactory() {
    }

    public static AbstractLogisticsFactory instance() {
        if (instance == null) {
            instance = new LogisticsFactory();
        }
        return instance;
    }

    public abstract ILogisticsModel createLogisticsModel();
    public abstract ILogisticsService createLogisticsService();
    public abstract IResponse createResponse();
    public abstract IEdge createEdge();
    public abstract IEdge createEdge(int node1x, int node1y, int node2x, int node2y);
    public abstract ICargo createCargo(int baggageId);
}
