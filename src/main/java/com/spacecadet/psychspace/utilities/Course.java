package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by marleneshankar on 4/14/17.
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String userKey;
    private String courseKey;
    private String name;
    private String instructor;
    private String description;
    private String startDate;
    private String endDate;
    private String enrollDate;
    private String dropDate;
    private String status;
    private String currSize;
    private String capacity;
}
