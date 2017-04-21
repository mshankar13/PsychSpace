package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.*;

import java.util.*;

/**
 * Created by marleneshankar on 3/18/17.
 */
public class CommentManager {

    private DatastoreService datastore;

    /**
     * constructor for connecting to datastore
     */
    public CommentManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * load all comments on single article
     * @param newsKey article key
     * @return arraylist of all comments
     */
    public ArrayList<Comment> loadComments(String newsKey) {
        Query newsCommentsQuery = new Query("Comment", KeyFactory.stringToKey(newsKey));
        List<Entity> newsComments =
                datastore.prepare(newsCommentsQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Comment> loadedComments = new ArrayList<Comment>();
        for (Entity entity : newsComments) {
            Comment comment = new Comment();
            comment.setUsername(entity.getProperty("Username").toString());
            comment.setContent(entity.getProperty("Content").toString());
            comment.setCommentKey(KeyFactory.keyToString(entity.getKey()));
            comment.setNewsKey(newsKey);

            loadedComments.add(comment);
        }
        return loadedComments;
    }

    /**
     * add new comment on article
     * @param newsKey article key
     * @param username user key
     * @param content comment content
     */
    public void addComment(String newsKey, String username, String content) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity comment = new Entity("Comment", KeyFactory.stringToKey(newsKey));
            comment.setProperty("Username", username);
            comment.setProperty("Content", content);

            datastore.put(txn, comment);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edit comment on article
     * @param commentKey comment key
     * @param username user key
     * @param content comment new content
     */
    public void editComment(String commentKey, String username, String content) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedComment = datastore.get(KeyFactory.stringToKey(commentKey));
                updatedComment.setProperty("Username", username);
                updatedComment.setProperty("Content", content);

                datastore.put(updatedComment);
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
     * delete comment on article
     * @param commentKey comment key
     */
    public void deleteComment(String commentKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(commentKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
