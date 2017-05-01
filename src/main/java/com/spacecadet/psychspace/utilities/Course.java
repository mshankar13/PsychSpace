package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Course
 * A course offered by an instructor.
 * Created by marleneshankar on 4/14/17.
 * modified by aliao on 4/28/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * instructor key in course entity created by datastore
     */
    private String userKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * title of the course
     */
    private String title;

    /**
     * name of the instructor
     */
    private String instructor;

    /**
     * description of the course
     */
    private String description;

    /**
     * start date of the course
     */
    private String startDate;

    /**
     * end date of the course
     */
    private String endDate;

    /**
     * enrollment date of the course
     */
    private String enrollDate;

    /**
     * drop date of the course
     */
    private String dropDate;

    /**
     * status of the course
     * cases: open or close
     */
    private String status;

    /**
     * current course size
     */
    private String currSize;

    /**
     * course capacity (maximum size)
     */
    private String capacity;
}
