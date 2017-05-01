package com.spacecadet.psychspace.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Datastore Entity: Article.
 * - An article in news page.
 * Created by marleneshankar on 3/18/17.
 * modified by aliao on 5/1/17.
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    /**
     * title of the article
     */
    private String title;

    /**
     * author name of the article
     */
    private String author;

    /**
     * content of the article
     */
    private String content;

    /**
     * like counts of the article
     */
    private String likesCount;

    /**
     * date of the article
     */
    private String date;

    /**
     *
     */
    private String newsKey;
}
