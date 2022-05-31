/**
 * @author Parth Shah
 */
package com.csci5308.group7.user.abstractFactory;

import com.csci5308.group7.user.interfaces.*;
import com.csci5308.group7.user.*;
import com.csci5308.group7.user.UserModelMock;

public class UserFactoryMock extends AbstractUserFactoryMock {

    @Override
    public IUser createUserMock() {
        return new User();
    }

    @Override
    public IUserModel createUserModelMock() {
        return new UserModelMock();
    }

    @Override
    public ILogin createLoginMock() {
        return new Login();
    }

    @Override
    public ISignup createSignupMock() {
        return new Signup();
    }

}
