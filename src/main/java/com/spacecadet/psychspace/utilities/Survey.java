package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Datastore Entity: Survey.
 * - A collection of questions to get user information.
 * - Each course has one survey.
 * Created by marleneshankar on 4/17/17.
 * modified by aliao on 5/1/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    /**
     * key in survey entity created by datastore
     */
    private String surveyKey;

    /**
     * key of the instructor/student in user entity created by datastore
     */
    private String userKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * title of the course
     */
    private String courseTitle;

    /**
     * title of the survey
     */
    private String title;

    /**
     * due date of the survey
     */
    private String dueDate;

    /**
     * collection of questions and answers in the survey
     */
    private transient HashMap<Question, ArrayList<Answer>> questions;
}
