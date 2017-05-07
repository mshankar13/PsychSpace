package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by aliao on 5/6/2017.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {

    /**
     * key in forum entity created by datastore
     */
    private String threadKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * user who started the forum
     * key created by datastore
     */
    private String userKey;

    /**
     * user name to show in forum
     * cases: user name from datastore or anonymous
     */
    private String inThreadName;

    /**
     * title of the forum
     */
    private String title;

    /**
     * content of the forum
     */
    private String content;
}
