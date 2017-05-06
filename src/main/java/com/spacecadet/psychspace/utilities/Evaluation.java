package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Evaluation.
 * - A daily report by students of how much is accomplished for goal.
 * Created by aliao on 4/10/2017.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    /**
     * key of the student in user entity created by datastore
     */
    private String authorKey;

    /**
     * name of the student
     */
    private String author;

    /**
     * content of the evaluation
     */
    private String content;

    /**
     * date of the evaluation
     */
    private String date;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * key in evaluation entity created by datastore
     */
    private String evaluationKey;

    /**
     * percentage score of the evaluation
     * what the user did divide by the goal
     */
    private String score;

    /**
     * score amount of user input
     */
    private String rawScore;
}

