package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by aliao on 4/10/2017.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private String authorKey;
    private String author;
    private String title;
    private String content;
    private String date;
    private String courseKey;
    private String evaluationKey;
    private String score;
}

