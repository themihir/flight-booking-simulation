/**
 * @author Chandan Shukla
 */

package com.csci5308.group7.webcheckin.interfaces;

import java.sql.SQLException;

public interface IWebCheckin {

    public void webCheckin(IRequest webCheckinRequest, IResponse webCheckinResponse,IWebCheckinModel webCheckinModel) throws SQLException;

    public void checkout(IRequest webCheckinRequest, IResponse webCheckinResponse, IWebCheckinModel webCheckinModel);
}
