package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.*;

import java.util.*;

/**
 * Created by marleneshankar on 3/18/17.
 */
public class CommentManager {
    private DatastoreService datastore;

    public CommentManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public ArrayList<Comment> loadComments(String newsID) {

        Query newsCommentsQuery = new Query("Comment", KeyFactory.stringToKey(newsID));
        List<Entity> newsComments =
                datastore.prepare(newsCommentsQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Comment> loadedComments = new ArrayList<Comment>();
        for (Entity entity : newsComments) {
            Comment comment = new Comment();
            comment.username = entity.getProperty("Username").toString();
            comment.content = entity.getProperty("Content").toString();
            comment.commentKey = KeyFactory.keyToString(entity.getKey());
            comment.newsKey = newsID;

            loadedComments.add(comment);
        }

        return loadedComments;

    }

    public void addComment(String newsID, String username, String content) {

        Transaction txn = datastore.beginTransaction();

        try {
            Entity comment = new Entity("Comment", KeyFactory.stringToKey(newsID));
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

    public void editComment(String commentID, String username, String content) {

        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedComment = datastore.get(KeyFactory.stringToKey(commentID));
                updatedComment.setProperty("Username", username);
                updatedComment.setProperty("Content", content);

                datastore.put(updatedComment);
                txn.commit();

            } catch (EntityNotFoundException ex) {

            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public void deleteComment(String commentID) {

        Transaction txn = datastore.beginTransaction();

        try {

            datastore.delete(KeyFactory.stringToKey(commentID));
            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
