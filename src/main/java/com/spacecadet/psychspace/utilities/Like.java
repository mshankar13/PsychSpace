package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Like.
 * - Action when a user like/unlike an article in news.
 * Created by aliao on 4/19/2017.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    /**
     * key in user entity created by datastore
     */
    private String userKey;

    /**
     * key in article entity created by datastore
     */
    private String articleKey;

    /**
     * staus of like
     * cases: like or unlike
     */
    private String status;

    /**
     * key in like entity created by datastore
     */
    private String likeKey;
}
