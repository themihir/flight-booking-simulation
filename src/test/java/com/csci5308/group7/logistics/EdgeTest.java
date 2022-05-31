/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactoryMock;
import com.csci5308.group7.logistics.interfaces.IEdge;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    AbstractLogisticsFactoryMock logisticsFactory = null;
    IEdge edge = null;

    @BeforeEach
    void setUp() {
        logisticsFactory = AbstractLogisticsFactoryMock.instance();
        edge = logisticsFactory.createEdgeMock();
    }

    @AfterEach
    void tearDown() {
        logisticsFactory = null;
        edge = null;
    }

    @Test
    void getCost() {
        assertEquals(0, edge.getCost());

        edge.setWeight(0);
        edge.setNode1x(0);
        edge.setNode1y(0);
        edge.setNode2x(0);
        edge.setNode2y(0);
        assertEquals(0, edge.getCost());

        edge.setWeight(10);
        edge.setNode1x(10);
        edge.setNode1y(10);
        edge.setNode2x(20);
        edge.setNode2y(20);
        assertEquals(141, Math.round(edge.getCost()));

        edge.setWeight(54);
        edge.setNode1x(0);
        edge.setNode1y(89);
        edge.setNode2x(0);
        edge.setNode2y(0);
        assertEquals(4806, edge.getCost());

        edge.setWeight(-1);
        edge.setNode1x(-1);
        edge.setNode1y(0);
        edge.setNode2x(-10);
        edge.setNode2y(0);
        assertEquals(-9, Math.round(edge.getCost()));
    }

    @Test
    void getDistance() {
        assertEquals(0, edge.getDistance());

        edge.setNode1x(0);
        edge.setNode1y(0);
        edge.setNode2x(0);
        edge.setNode2y(0);
        assertEquals(0, edge.getDistance());

        edge.setNode1x(10);
        edge.setNode1y(10);
        edge.setNode2x(20);
        edge.setNode2y(20);
        assertEquals(14, Math.round(edge.getDistance()));

        edge.setNode1x(0);
        edge.setNode1y(89);
        edge.setNode2x(0);
        edge.setNode2y(0);
        assertEquals(89, edge.getDistance());

        edge.setNode1x(-1);
        edge.setNode1y(0);
        edge.setNode2x(-10);
        edge.setNode2y(0);
        assertEquals(9, Math.round(edge.getDistance()));
    }
}