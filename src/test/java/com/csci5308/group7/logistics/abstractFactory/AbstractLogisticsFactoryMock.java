/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.abstractFactory;

import com.csci5308.group7.logistics.interfaces.*;

public abstract class AbstractLogisticsFactoryMock {
    private static LogisticsFactoryMock instance = null;

    protected void AbstractLogisticsFactory() {
    }

    public static LogisticsFactoryMock instance() {
        if (instance == null) {
            instance = new LogisticsFactoryMock();
        }
        return instance;
    }

    public abstract ILogisticsModel createLogisticsModelMock();
    public abstract ILogisticsService createLogisticsServiceMock();
    public abstract IResponse createResponseMock();
    public abstract IEdge createEdgeMock();
    public abstract IEdge createEdgeMock(int node1x, int node1y, int node2x, int node2y);
}
