/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.logistics.abstractFactory.AbstractLogisticsFactoryMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckpointTest {

    AbstractLogisticsFactoryMock logisticsFactory = null;

    @BeforeEach
    void setUp() {
        logisticsFactory = AbstractLogisticsFactoryMock.instance();
    }

    @AfterEach
    void tearDown() {
        logisticsFactory = null;
    }

    @Test
    void getRandomCheckpoint() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        Map<String, Object> map = Checkpoint.getRandomCheckpoint(list);
        assertNull(map);

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        list.add(map1);
        assertEquals(map1, Checkpoint.getRandomCheckpoint(list));

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        list.add(map2);
        assertEquals(map1, Checkpoint.getRandomCheckpoint(list));
    }
}