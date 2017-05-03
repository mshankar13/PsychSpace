package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Habit.
 * - A habit records the cue that triggers the action(the goal).
 * Created by marleneshankar on 4/26/17.
 * modified by aliao on 5/1/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    /**
     * key in habit entity created by datestore
     */
    private String habitKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * name of the user
     */
    private String username;

    /**
     * key in cue entity created by datastore
     * the cue that triggers the action
     */
    private String cueKey;

    /**
     * key in goal entity created by datastore
     */
    private String goalKey;

    /**
     * reward set by the user when the goal is achieved
     */
    private String reward;
}
