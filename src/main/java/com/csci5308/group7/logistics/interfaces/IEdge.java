/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics.interfaces;

public interface IEdge{
    int getWeight();
    void setWeight(int weight);
    int getNode1x();
    void setNode1x(int node1x);
    int getNode1y();
    void setNode1y(int node1y);
    int getNode2x();
    void setNode2x(int node2x);
    int getNode2y();
    void setNode2y(int node2y);
    double getDistance();
    double getCost();
}
