package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Comment
 * - A comment towards a news article.
 * Created by marleneshankar on 3/18/17.
 * modified by aliao on 4/28/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * name of the user
     */
    private String username;

    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * content of the comment
     */
    private String content;

    /**
     * key in comment entity created by datastore
     */
    private String commentKey;

    /**
     * key in news entity created by datastore
     */
    private String newsKey;

    /**
     * date of the comment
     */
    private String date;

    /**
     * status of the comment being made in frontend,
     * for controller to differentiate
     * cases: null, add, edit, delete
     */
    private String state;
}
