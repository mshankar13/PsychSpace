package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Created by marleneshankar on 3/18/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String userKey;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public Date dob;
    public String role; //User, Instructor, Admin
}
