package com.spacecadet.psychspace.responses;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by aliao on 4/6/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonUser {
    @JsonView(JsonViews.Public.class)
    String username;

    String password;

    @JsonView(JsonViews.Public.class)
    String email;
}
