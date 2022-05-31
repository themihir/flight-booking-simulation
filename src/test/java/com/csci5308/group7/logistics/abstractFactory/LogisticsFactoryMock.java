/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.abstractFactory;

import com.csci5308.group7.logistics.*;
import com.csci5308.group7.logistics.interfaces.*;

public class LogisticsFactoryMock extends AbstractLogisticsFactoryMock {
    @Override
    public ILogisticsModel createLogisticsModelMock() {
        return new LogisticsModelMock();
    }

    @Override
    public ILogisticsService createLogisticsServiceMock() {
        return new LogisticsService();
    }

    @Override
    public IResponse createResponseMock() {
        return new Response();
    }

    @Override
    public IEdge createEdgeMock() {
        return new Edge();
    }

    @Override
    public IEdge createEdgeMock(int node1x, int node1y, int node2x, int node2y) {
        return new Edge(node1x, node1y, node2x, node2y);
    }
}
