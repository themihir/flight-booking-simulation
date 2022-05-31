/**
 * @author Chandan Shukla
 */
package com.csci5308.group7.search.interfaces;

import java.util.Map;

public interface IFareCalculator {
    double multiplyingFactor(int ClassSeats,int ClassCapacity);
    double dynamicfare(Map<String, Object> result, IRequest searchRequest);
}
