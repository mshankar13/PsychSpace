package com.spacecadet.psychspace.requests;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by aliao on 3/27/2017.
 */
public class AuthenticateUserRequest {
    private String email;
    private String firstName;
    private String lastName;

    public String getEmail(){
        return email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public  void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}
