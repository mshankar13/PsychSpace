package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Cue
 * - A trigger (action, thought, object, etc.) that causes certain actions after.
 * Created by marleneshankar on 4/26/17.
 * modified by aliao on 4/28/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cue {

    /**
     * key in Cue entity created by datastore
     */
    private String cueKey;

    /**
     * key in User entity created by datastore
     */
    private String userKey;

    /**
     * type of the cue
     * cases: positive and negative
     */
    private String type;

    /**
     * location of the cue
     * (where the cue happened/triggered)
     */
    private String location;

    /**
     * time of the cue
     * (when the cue happened/triggered)
     */
    private String time;

    /**
     * emotion when the cue is triggered
     */
    private String emotionalState;

    /**
     * environment of the cue
     * the surroundings (people around, etc.)
     */
    private String environment;

    /**
     * what action happened right before the cue
     */
    private String action;
}

