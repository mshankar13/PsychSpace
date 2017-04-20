package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormat;
import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormatter;
import com.spacecadet.psychspace.utilities.*;
import lombok.experimental.Helper;

import java.sql.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author marleneshankar
 */
public class NewsManager {
    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

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
            news.setTitle(entity.getProperty("Title").toString());
            news.setAuthor(entity.getProperty("Author").toString());
            news.setContent(entity.getProperty("Content").toString());
            news.setLikesCount(entity.getProperty("Likes").toString());
            news.setDate(entity.getProperty("Date").toString());
            news.setNewsKey(KeyFactory.keyToString(entity.getKey()));
            loadedNews.add(news);

        }

        // Sort the loaded News by date
        Collections.sort(loadedNews, new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                Date date1 = helper.stringToDate(o1.getDate());
                Date date2 = helper.stringToDate(o2.getDate());
                return date2.compareTo(date1);
            }
        });

        return loadedNews;
    }

    /**
     * Load a single news article with corresponding comments
     *
     * @param newsKey
     * @return
     */
    public News loadSingleNews(String newsKey) {

        News news = new News();
        try {
            Entity singleNews = datastore.get(KeyFactory.stringToKey(newsKey));
            news.setTitle(singleNews.getProperty("Title").toString());
            news.setAuthor(singleNews.getProperty("Author").toString());
            news.setContent(singleNews.getProperty("Content").toString());
            news.setLikesCount(singleNews.getProperty("Likes").toString());
            news.setDate(singleNews.getProperty("Date").toString());
            news.setNewsKey(newsKey);

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
    public void addNews(String title, String author, String content, String likesCount, String date) {

        Transaction txn = datastore.beginTransaction();
        try {
            Entity news = new Entity("News");
            news.setProperty("Title", title);
            news.setProperty("Author", author);
            news.setProperty("Content", content);
            news.setProperty("Likes", likesCount);
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
     * @param newsKey
     * @param title
     * @param author
     * @param content
     * @param likesCount
     */
    public void editNews(String newsKey, String title, String author, String content, String likesCount, String date) {

        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedNews = datastore.get(KeyFactory.stringToKey(newsKey));
                updatedNews.setProperty("Title", title);
                updatedNews.setProperty("Author", author);
                updatedNews.setProperty("Content", content);
                updatedNews.setProperty("Likes", likesCount);
                updatedNews.setProperty("Date", date);

                datastore.delete(KeyFactory.stringToKey(newsKey));
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
     * @param newsKey
     */
    public void deleteNews(String newsKey) {
        Transaction txn = datastore.beginTransaction();

        try {
            datastore.delete(KeyFactory.stringToKey(newsKey));
            txn.commit();

            // Delete all comments associated with the news entry
            Query newsCommentsQuery = new Query("Comment", KeyFactory.stringToKey(newsKey));
            List<Entity> listComments =
                    datastore.prepare(newsCommentsQuery).asList(FetchOptions.Builder.withDefaults());

            for (Entity entry : listComments) {
                datastore.delete(entry.getKey());
            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * Gets the featured article from the list of loaded news
     * Returns latest article if there is no article within the week
     * @param allNews
     * @return
     */
    public News getFeatured(ArrayList<News> allNews) {

        ArrayList<News> potentialFeatured = new ArrayList<>();
        Date lastWeek = new DateTime().minusDays(7).toDate();
        Date today = new Date();
        System.out.println(today.after(lastWeek));
        for (News news : allNews) {
            Date date = helper.stringToDate(news.getDate());
            if (date.after(lastWeek) == true) {
                potentialFeatured.add(news);
            }
        }

        if (potentialFeatured.size() != 0) {
            Collections.sort(potentialFeatured, new Comparator<News>() {
                @Override
                public int compare(News o1, News o2) {
                    return Integer.parseInt(o1.getLikesCount()) - Integer.parseInt(o2.getLikesCount());
                }
            });
            return potentialFeatured.get(potentialFeatured.size() - 1);
        }
        return allNews.get(allNews.size() - 1);
    }
}
