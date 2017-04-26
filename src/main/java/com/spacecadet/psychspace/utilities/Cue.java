package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by marleneshankar on 4/26/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cue {
    private String cueKey;
    private String userKey;
    private String type;
    private String location;
    private String time;
    private String emotionalState;
    private String environment;
    private String action;
}

