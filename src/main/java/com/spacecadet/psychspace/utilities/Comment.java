package com.spacecadet.psychspace.utilities;

import com.google.appengine.api.datastore.Key;
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
    public String username;
    public String content;
    public String commentKey;
    public String newsKey;
}
