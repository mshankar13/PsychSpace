package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Answer.
 * - An answer to a survey question.
 * Created by marleneshankar on 4/19/17.
 * modified by aliao on 4/28/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * key in question entity created by datastore
     */
    private String questionKey;

    /**
     * answer of a question
     */
    private String answer;

    /**
     * score of the answer
     */
    private String score;
}
