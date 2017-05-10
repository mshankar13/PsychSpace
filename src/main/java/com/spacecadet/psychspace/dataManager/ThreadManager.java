package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Thread;

import java.util.ArrayList;

/**
 * Created by marleneshankar on 5/9/17.
 */

public class ThreadManager {

    private DatastoreService datastore;

    /**
     * constructor
     */
    public ThreadManager() { datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     *
     * @param courseKey
     * @return
     */
    public ArrayList<Thread> loadCourseThreads(String courseKey) {
        ArrayList<Thread> loadedThreads = new ArrayList<>();

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
     * @param thread
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
     * @param threadKey
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
