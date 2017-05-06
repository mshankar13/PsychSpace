package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.spacecadet.psychspace.utilities.*;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author marleneshankar
 */
public class NewsManager {

    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

    /**
     * constructor
     */
    public NewsManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Loads the lists of news titles and their likes count
     */
    public ArrayList<Article> loadNews() {
        Query newsQuery = new Query("Article");
        List<Entity> newsList =
                datastore.prepare(newsQuery).asList(FetchOptions.Builder.withDefaults());
        ArrayList<Article> loadedNews = new ArrayList<Article>();
        for (Entity entity : newsList) {
            Article article = new Article();
            article.setTitle(entity.getProperty("Title").toString());
            article.setAuthor(entity.getProperty("Author").toString());
            article.setContent(entity.getProperty("Content").toString());
            article.setLikesCount(entity.getProperty("Likes").toString());
            article.setDate(entity.getProperty("Date").toString());
            article.setNewsKey(KeyFactory.keyToString(entity.getKey()));
            loadedNews.add(article);
        }
        // Sort the loaded Article by date
        Collections.sort(loadedNews, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
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
     * @param newsKey single news key
     * @return news utility
     */
    public Article loadSingleNews(String newsKey) {
        Article article = new Article();
        try {
            Entity singleNews = datastore.get(KeyFactory.stringToKey(newsKey));
            article.setTitle(singleNews.getProperty("Title").toString());
            article.setAuthor(singleNews.getProperty("Author").toString());
            article.setContent(singleNews.getProperty("Content").toString());
            article.setLikesCount(singleNews.getProperty("Likes").toString());
            article.setDate(singleNews.getProperty("Date").toString());
            article.setNewsKey(newsKey);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        }
        return article;
    }

    /**
     * Adds a news entity to datastore
     *
     * @param title      news title
     * @param author     news author
     * @param content    news content
     * @param likesCount news like count
     */
    public void addNews(String title, String author, String content, String likesCount, String date) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity news = new Entity("Article");
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
     * @param newsKey    news key
     * @param title      news title
     * @param author     news author
     * @param content    news content
     * @param likesCount news like count
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
                ex.printStackTrace();
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
     * @param newsKey news key
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
     * Gets the featured article from the list of loaded articles
     * Returns latest article if there is no article within the week
     *
     * @param allNews arraylist of all article objects
     * @return single featured article object
     */
    public Article getFeatured(ArrayList<Article> allNews) {
        ArrayList<Article> potentialFeatured = new ArrayList<>();
        Date lastWeek = new DateTime().minusDays(7).toDate();
        if (allNews.isEmpty()){
            return null;
        }
        for (Article article : allNews) {
            Date date = helper.stringToDate(article.getDate());
            if (date.after(lastWeek) == true) {
                potentialFeatured.add(article);
            }
        }
        if (potentialFeatured.size() != 0) {
            Collections.sort(potentialFeatured, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return Integer.parseInt(o1.getLikesCount()) - Integer.parseInt(o2.getLikesCount());
                }
            });
            return potentialFeatured.get(potentialFeatured.size() - 1);
        }
        return allNews.get(allNews.size() - 1);
    }

    /**
     * search for news with related keyword
     * @param articleList list of all news
     * @param keyword  user input keyword
     * @return list of all related news
     */
    public ArrayList<Article> searchNews(ArrayList<Article> articleList, String keyword) {
        ArrayList<Article> titleSearch = new ArrayList<>();
        for (Article article : articleList) {
            if (Pattern.compile(Pattern.quote(keyword),
                    Pattern.CASE_INSENSITIVE).matcher(article.getTitle()).find()) {
                titleSearch.add(article);
            }
        }
        Collections.sort(titleSearch, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        ArrayList<Article> contentSearch = new ArrayList<>();
        for (Article article : articleList) {
            if(Pattern.compile(Pattern.quote(keyword),
                    Pattern.CASE_INSENSITIVE).matcher(article.getContent()).find()){
                if (!titleSearch.contains(article)) {
                    contentSearch.add(article);
                }
            }
        }
        Collections.sort(contentSearch, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        ArrayList<Article> results = new ArrayList<>();
        results.addAll(titleSearch);
        results.addAll(contentSearch);
        return results;
    }
}
