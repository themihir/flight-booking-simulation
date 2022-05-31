/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.abstractFactory;

import com.csci5308.group7.logistics.*;
import com.csci5308.group7.logistics.interfaces.*;
import com.csci5308.group7.logistics.transit.Cargo;
import com.csci5308.group7.logistics.transit.interfaces.ICargo;

public class LogisticsFactory extends AbstractLogisticsFactory {

    @Override
    public ILogisticsModel createLogisticsModel() {
        return new LogisticsModel();
    }

    @Override
    public ILogisticsService createLogisticsService() {
        return new LogisticsService();
    }

    @Override
    public IResponse createResponse() {
        return new Response();
    }

    @Override
    public IEdge createEdge() {
        return new Edge();
    }

    @Override
    public IEdge createEdge(int node1x, int node1y, int node2x, int node2y) {
        return new Edge(node1x, node1y, node2x, node2y);
    }

    @Override
    public ICargo createCargo(int baggageId) {
        return new Cargo(baggageId);
    }
}
