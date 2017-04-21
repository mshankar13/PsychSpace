package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by marleneshankar on 3/18/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String username; //userkey
    private String content;
    private String commentKey;
    private String newsKey;
    private String date;
    private String state; //null, add, edit, delete
}
