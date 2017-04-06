package com.spacecadet.psychspace.responses;

/**
 * Created by aliao on 4/6/2017.
 */
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResponseBody {

    @JsonView(JsonViews.Public.class)
    String msg;

    @JsonView(JsonViews.Public.class)
    String code;

//    @JsonView(JsonViews.Public.class)
//    List<User> result;
}