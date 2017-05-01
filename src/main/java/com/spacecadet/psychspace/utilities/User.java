package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Datastore Entity: User.
 * - User data of the users logged in to PsychSpace.
 * Created by marleneshankar on 3/18/17.
 * modified by aliao on 5/1/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * google email logged in by the user
     */
    private String email;

    /**
     * first name of the user
     */
    private String firstName;

    /**
     * last name of the user
     */
    private String lastName;

    /**
     * user type
     * cases: user (regular user), instructor, admin
     */
    private String role;
}
