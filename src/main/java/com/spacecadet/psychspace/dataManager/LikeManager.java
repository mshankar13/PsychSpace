package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Like;

import java.util.*;

/**
 * Created by marleneshankar on 4/14/17.
 * Modified by aliao on 4/19/17
 */
public class LikeManager {

    private DatastoreService datastore;

    public LikeManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * load all like records in datastore
     * @return list of existing like records
     */
    public ArrayList<Like> loadLikes() {
        Query likeQuery = new Query("Like");
        List<Entity> likeList =
                datastore.prepare(likeQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Like> loadedLikes = new ArrayList<Like>();
        for (Entity entity : likeList) {
            Like like = new Like();
            like.setUserID(entity.getProperty("userID").toString());
            like.setArticleID(entity.getProperty("articleID").toString());
            like.setStatus(entity.getProperty("status").toString());
            like.setLikeKey(KeyFactory.keyToString(entity.getKey()));
            loadedLikes.add(like);
        }

        return loadedLikes;
    }

    /**
     * adds new Like entity to datastore when user likes an article
     * @param newsManager
     * @param newsID
     * @param title
     * @param author
     * @param content
     * @param likesCount
     * @param date
     * @param like
     */
    public void like (NewsManager newsManager, String newsID, String title, String author, String content,
                      String likesCount, String date, Like like) {
        int count = Integer.parseInt(likesCount);
        count++;
        newsManager.editNews(newsID, title, author, content, Integer.toString(count), date);

        Transaction txn = datastore.beginTransaction();
        try {
            Entity newLike = new Entity("Like");
            newLike.setProperty("userID", like.getUserID());
            newLike.setProperty("articleID", like.getArticleID());
            newLike.setProperty("status", like.getStatus());
            datastore.put(txn, newLike);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * modifies the like entity to unlike when user choose to unlike
     * @param newsManager
     * @param newsID
     * @param title
     * @param author
     * @param content
     * @param likesCount
     * @param date
     */
    public void unlike (NewsManager newsManager, String newsID, String title, String author, String content,
                      String likesCount, String date, Like like) {
        int count = Integer.parseInt(likesCount);
        count--;
        newsManager.editNews(newsID, title, author, content, Integer.toString(count), date);

        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedLike = datastore.get(KeyFactory.stringToKey(like.getLikeKey()));
                updatedLike.setProperty("userID", like.getUserID());
                updatedLike.setProperty("articleID", like.getArticleID());
                updatedLike.setProperty("status", like.getStatus());

                datastore.delete(KeyFactory.stringToKey(like.getLikeKey()));
                datastore.put(updatedLike);
                txn.commit();

            } catch (EntityNotFoundException ex) {

            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
