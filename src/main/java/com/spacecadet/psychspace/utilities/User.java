package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Created by marleneshankar on 3/18/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userKey; // Created by the datastore
    private String email; // user gmail
    private String firstName;
    private String lastName;
    private String role; //User, Instructor, Admin
}
