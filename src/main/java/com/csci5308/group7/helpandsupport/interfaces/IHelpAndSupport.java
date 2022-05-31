/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport.interfaces;


public interface IHelpAndSupport {
    public void getVoucher(IRequest request, IHelpAndSupportModel model, IResponse response);
    public void makePendingPayment(IRequest request, IHelpAndSupportModel model, IResponse response);
    public void getFeedback(IRequest request, IHelpAndSupportModel model, IResponse response);
}
