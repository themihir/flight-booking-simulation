/**
 * @author Parth Shah, Mihir Sanchaniya, Mukund Sharma, Chandan Shukla
 */
package com.csci5308.group7;

import com.csci5308.group7.utilities.State;
import com.csci5308.group7.utilities.interfaces.IState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Group7Application {
    public static void main(String[] args) {
        IState state = State.getInstance();
        state.setArguments(args);
        SpringApplication.run(Group7Application.class, args);
    }
}
