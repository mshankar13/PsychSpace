package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marleneshankar on 5/9/17.
 * modified by aliao on 5/10/17.
 */

public class ThreadManager {

    private DatastoreService datastore;

    /**
     * constructor
     */
    public ThreadManager() { datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     * loads a single thread for given thread key in datastore
     * @param threadKey thread key in datastore
     * @return thread utility object
     */
    public Thread loadSingleThread(String threadKey) {
        Thread thread = new Thread();
        try {
            Entity foundThread = datastore.get(KeyFactory.stringToKey(threadKey));
            thread.setThreadKey(KeyFactory.keyToString(foundThread.getKey()));
            thread.setUserKey(foundThread.getProperty("UserKey").toString());
            thread.setCourseKey(foundThread.getProperty("CourseKey").toString());
            thread.setInThreadName(foundThread.getProperty("InThreadName").toString());
            thread.setTitle(foundThread.getProperty("Title").toString());
            thread.setDate(foundThread.getProperty("Date").toString());
            thread.setContent(foundThread.getProperty("Content").toString());
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        return thread;
    }

    /**
     * loads the threads for a given course
     * @param courseKey course key in datastore
     * @return array list of thread with specified course
     */
    public ArrayList<Thread> loadCourseThreads(String courseKey) {
        ArrayList<Thread> loadedThreads = new ArrayList<>();
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query threadQuery = new Query("Thread").setFilter(propertyFilter1);
        List<Entity> foundThreads = datastore.prepare(threadQuery).asList(FetchOptions.Builder.withDefaults());
        for (Entity thread: foundThreads) {
            Thread thread1 = new Thread();
            thread1.setThreadKey(KeyFactory.keyToString(thread.getKey()));
            thread1.setUserKey(thread.getProperty("UserKey").toString());
            thread1.setUserKey(thread.getProperty("CourseKey").toString());
            thread1.setInThreadName(thread.getProperty("InThreadName").toString());
            thread1.setTitle(thread.getProperty("Title").toString());
            thread1.setContent(thread.getProperty("Content").toString());
            thread1.setContent(thread.getProperty("Date").toString());

            loadedThreads.add(thread1);
        }
        return loadedThreads;
    }

    /**
     * adds a thread to datastore
     * @param thread new thread
     */
    public void addThread(Thread thread) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity thread1 = new Entity("Thread");
            thread1.setProperty("CourseKey", thread.getCourseKey());
            thread1.setProperty("UserKey", thread.getUserKey());
            thread1.setProperty("InThreadName", thread.getInThreadName());
            thread1.setProperty("Title", thread.getTitle());
            thread1.setProperty("Content", thread.getContent());
            thread1.setProperty("Date", thread.getDate());
            datastore.put(txn, thread1);
            txn.commit();
            thread.setThreadKey(KeyFactory.keyToString(thread1.getKey()));
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edits a thread in datastore
     * @param thread edited thread
     */
    public void editThread(Thread thread) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedThread = datastore.get(KeyFactory.stringToKey(thread.getThreadKey()));
                updatedThread.setProperty("CoruseKey", thread.getCourseKey());
                updatedThread.setProperty("UserKey", thread.getUserKey());
                updatedThread.setProperty("InThreadName", thread.getInThreadName());
                updatedThread.setProperty("Title", thread.getTitle());
                updatedThread.setProperty("Content", thread.getContent());
                updatedThread.setProperty("Date", thread.getDate());
                datastore.delete(KeyFactory.stringToKey(thread.getThreadKey()));
                datastore.put(updatedThread);
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
     * deletes a thread in datastore
     * @param threadKey thread key
     */
    public void deleteThread(String threadKey){
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(threadKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
