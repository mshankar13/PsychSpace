package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Enroll.
 * - Manage enrollment of students.
 * Created by marleneshankar on 4/14/17.
 * modified by aliao on 5/1/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enroll {

    /**
     * key of the student in user entity created by datastore
     */
    private String userKey;

    /**
     * key of the course in course entity created by datastore
     */
    private String courseKey;
}
