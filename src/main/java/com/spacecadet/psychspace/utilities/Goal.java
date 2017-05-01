package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Goal
 * - The object of a person's ambition or effort; an aim or desired result
 * - The user will declare a goal for each course.
 * Created by marleneshankar on 4/26/17.
 * modified by aliao on 5/1/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goal {

    /**
     * the target routine (read book, etc.)
     */
    private String goalName;

    /**
     * key in goal entity created by datastore
     */
    private String goalKey;

    /**
     * name of the user
     */
    private String username;

    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * value of the goal (1, etc.)
     */
    private String value;

    /**
     * unit of the value (chapter, etc.)
     */
    private String unit;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;
}
