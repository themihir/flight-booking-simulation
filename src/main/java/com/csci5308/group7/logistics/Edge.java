/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.interfaces.IEdge;

public class Edge implements IEdge {
    private int weight;
    private int node1x;
    private int node1y;
    private int node2x;
    private int node2y;

    public Edge() {
    }

    public Edge(int node1x, int node1y, int node2x, int node2y) {
        this.node1x = node1x;
        this.node1y = node1y;
        this.node2x = node2x;
        this.node2y = node2y;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getNode1x() {
        return node1x;
    }

    @Override
    public void setNode1x(int node1x) {
        this.node1x = node1x;
    }

    @Override
    public int getNode1y() {
        return node1y;
    }

    @Override
    public void setNode1y(int node1y) {
        this.node1y = node1y;
    }

    @Override
    public int getNode2x() {
        return node2x;
    }

    @Override
    public void setNode2x(int node2x) {
        this.node2x = node2x;
    }

    @Override
    public int getNode2y() {
        return node2y;
    }

    @Override
    public void setNode2y(int node2y) {
        this.node2y = node2y;
    }

    @Override
    public double getDistance() {
        double pythagoras = Math.pow(node1x - node2x, 2) + Math.pow(node1y - node2y, 2);
        return Math.sqrt(pythagoras);
    }

    @Override
    public double getCost() {
        return weight * getDistance();
    }
}
