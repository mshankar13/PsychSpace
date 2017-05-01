package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Question.
 * - Each question entity in a survey.
 * Created by marleneshankar on 4/17/17.
 * modified by aliao on 5/1/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    /**
     * key in question entity created by datastore
     */
    private String questionKey;

    /**
     * key in survey entity created by datastore
     */
    private String surveyKey;

    /**
     * content of the question
     */
    private String content;

    /**
     * type of the question
     * (goal setting, prioritization, managing interruptions, procrastination, scheduling, etc.)
     */
    private String type;
}
