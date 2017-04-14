package com.spacecadet.psychspace.dataManager;

/**
 * Created by marleneshankar on 4/14/17.
 */
public class ArticleManager {

    public void like (NewsManager newsManager, String newsID, String title, String author, String content,
                      String likesCount, String date) {
        int count = Integer.parseInt(likesCount);
        count++;
        newsManager.editNews(newsID, title, author, content, Integer.toString(count), date);

    }

    public void unlike (NewsManager newsManager, String newsID, String title, String author, String content,
                      String likesCount, String date) {
        int count = Integer.parseInt(likesCount);
        count--;
        newsManager.editNews(newsID, title, author, content, Integer.toString(count), date);

    }
}
