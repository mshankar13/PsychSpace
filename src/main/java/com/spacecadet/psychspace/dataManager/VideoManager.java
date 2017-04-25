package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.News;
import com.spacecadet.psychspace.utilities.Video;

import java.util.*;

/**
 * Created by aliao on 4/24/2017.
 */
public class VideoManager {

    private DatastoreService datastore;

    /**
     * constructor
     */
    public VideoManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * loads the list of all videos
     * @return array list of videos
     */
    public ArrayList<Video> loadVideos() {
        Query videoQuery = new Query("Video");
        List<Entity> videoList =
                datastore.prepare(videoQuery).asList(FetchOptions.Builder.withDefaults());
        ArrayList<Video> loadedVideos = new ArrayList<Video>();
        for (Entity entity : videoList) {
            Video video = new Video();
            video.setTitle(entity.getProperty("Title").toString());
            video.setUrl(entity.getProperty("Url").toString());
            video.setCourseKey(entity.getProperty("CourseKey").toString());
            video.setVideoKey(KeyFactory.keyToString(entity.getKey()));
            loadedVideos.add(video);
        }
        return loadedVideos;
    }

    /**
     * Load a single video
     * @param videoKey video key
     * @return video utility
     */
    public Video loadSingleVideo(String videoKey) {
        Video video = new Video();
        try {
            Entity entity = datastore.get(KeyFactory.stringToKey(videoKey));
            video.setTitle(entity.getProperty("Title").toString());
            video.setUrl(entity.getProperty("Url").toString());
            video.setCourseKey(entity.getProperty("CourseKey").toString());
            video.setVideoKey(videoKey);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        }
        return video;
    }

    /**
     * adds a video entity to datastore
     * @param video added video
     */
    public void addVideo(Video video) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity entity = new Entity("Video");
            entity.setProperty("Title", video.getTitle());
            entity.setProperty("Url", video.getUrl());
            entity.setProperty("CourseKey", video.getCourseKey());
            datastore.put(txn, entity);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edits a video entity from datastore
     * @param video
     */
    public void editVideo(Video video) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity entity = datastore.get(KeyFactory.stringToKey(video.getVideoKey()));
                entity.setProperty("Title", video.getTitle());
                entity.setProperty("Url", video.getUrl());
                entity.setProperty("CourseKey", video.getCourseKey());

                datastore.delete(KeyFactory.stringToKey(video.getVideoKey()));
                datastore.put(entity);
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
     * delete a video entity from datastore
     * @param videoKey
     */
    public void deleteVideo(String videoKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(videoKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
