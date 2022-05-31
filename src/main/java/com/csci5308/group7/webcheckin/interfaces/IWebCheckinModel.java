/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.interfaces;

import java.sql.SQLException;
import java.util.HashMap;

public interface IWebCheckinModel {

    public HashMap<String,Object> getRecord(IRequest checinRequest) throws SQLException;

    public String postRecord(IRequest checinRequest);
}
