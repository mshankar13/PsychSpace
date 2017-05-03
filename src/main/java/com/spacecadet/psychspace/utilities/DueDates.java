package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: DueDates
 * due dates set by course instructor
 * Created by aliao on 5/3/2017.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DueDates {

    /**
     * key in dueDates entity created by datastore
     */
    private String dueDatesKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * due date of goal for course set by instructor
     */
    private String goalDueDate;

    /**
     * due date of cues for course set by instructor
     */
    private String cuesDueDate;

    /**
     * due date of habit for course set by instructor
     */
    private String habitDueDate;
}
