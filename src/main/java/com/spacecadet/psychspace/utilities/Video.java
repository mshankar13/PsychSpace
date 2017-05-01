package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Video.
 * - instructor added video to course.
 * - saves you tube url links to the videos.
 * Created by aliao on 4/21/2017.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    /**
     * title of the video
     */
    private String title;

    /**
     * youtube share/embed url link to the video
     */
    private String url;

    /**
     * key in video entity created by datastore
     */
    private String videoKey;

    /**
     * key in course entity created by datastore
     */
    private String courseKey;

    /**
     * title of the course
     */
    private String courseTitle;
}
