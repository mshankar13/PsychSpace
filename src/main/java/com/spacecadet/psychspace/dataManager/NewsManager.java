package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.spacecadet.psychspace.utilities.*;

import java.util.*;

/**
 * @author marleneshankar
 */
public class NewsManager {
    private DatastoreService datastore;

    public NewsManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Loads the lists of news titles and their likes count
     */
    public ArrayList<News> loadNews() {
        Query newsQuery = new Query("News");
        List<Entity> newsList =
                datastore.prepare(newsQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<News> loadedNews = new ArrayList<News>();
        for (Entity entity : newsList) {
            News news = new News();
            news.title = entity.getProperty("Title").toString();
            news.author = entity.getProperty("Author").toString();
            news.content = entity.getProperty("Content").toString();
            news.likesCount = Integer.parseInt(entity.getProperty("Likes").toString());
            news.date = news.convertDate(entity.getProperty("Date").toString());
            news.newsKey = KeyFactory.keyToString(entity.getKey());
            loadedNews.add(news);
        }

        // Sort the loaded News by date
        Collections.sort(loadedNews, new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                return o1.date.compareTo(o2.date);
            }
        });

        return loadedNews;
    }

    /**
     * Load a single news article with corresponding comments
     *
     * @param newsID
     * @return
     */
    public News loadSingleNews(String newsID) {

        News news = new News();
        try {
            Entity singleNews = datastore.get(KeyFactory.stringToKey(newsID));
            news.title = singleNews.getProperty("Title").toString();
            news.author = singleNews.getProperty("Author").toString();
            news.content = singleNews.getProperty("Content").toString();
            news.likesCount = Integer.parseInt(singleNews.getProperty("Likes").toString());
            news.date = news.convertDate(singleNews.getProperty("Date").toString());
            news.newsKey = newsID;

        } catch (EntityNotFoundException ex) {

        }

        return news;
    }

    /**
     * Adds a news Entity to datastore
     *
     * @param title
     * @param author
     * @param content
     * @param likesCount
     */
    public void addNews(String title, String author, String content, int likesCount, String date) {

        Transaction txn = datastore.beginTransaction();
        try {
            Entity news = new Entity("News");
            news.setProperty("Title", title);
            news.setProperty("Author", author);
            news.setProperty("Content", content);
            news.setProperty("Likes", Integer.toString(likesCount));
            news.setProperty("Date", date);
            datastore.put(txn, news);
            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * Edits a news Entity from datastore
     *
     * @param newsID
     * @param title
     * @param author
     * @param content
     * @param likesCount
     */
    public void editNews(String newsID, String title, String author, String content, int likesCount, String date) {

        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedNews = datastore.get(KeyFactory.stringToKey(newsID));
                updatedNews.setProperty("Title", title);
                updatedNews.setProperty("Author", author);
                updatedNews.setProperty("Content", content);
                updatedNews.setProperty("Likes", Integer.toString(likesCount));
                updatedNews.setProperty("Date", date);

                datastore.delete(KeyFactory.stringToKey(newsID));
                datastore.put(updatedNews);
                txn.commit();

            } catch (EntityNotFoundException ex) {

            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * Deletes a news Entity from datastore
     *
     * @param newsID
     */
    public void deleteNews(String newsID) {
        Transaction txn = datastore.beginTransaction();

        try {
            datastore.delete(KeyFactory.stringToKey(newsID));
            txn.commit();

            // Delete all comments associated with the news entry
            Query newsCommentsQuery = new Query("Comment", KeyFactory.stringToKey(newsID));
            List<Entity> listComments =
                    datastore.prepare(newsCommentsQuery).asList(FetchOptions.Builder.withDefaults());

            for (Entity entry: listComments) {
                datastore.delete(entry.getKey());
            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
