package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Cue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marleneshankar on 4/26/17.
 * modified by aliao on 5/3/17.
 */
public class CueManager {

    private DatastoreService datastore;

    /** constructor */
    public CueManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * load a single cue with cue key in datastore
     * @param cueKey cue key in datastore
     * @return cue utility object
     */
    public Cue loadSingleCue(String cueKey){
        Cue cue = new Cue();
        try {
            Entity foundCue = datastore.get(KeyFactory.stringToKey(cueKey));
            cue.setCueKey(KeyFactory.keyToString(foundCue.getKey()));
            cue.setUserKey(foundCue.getProperty("UserKey").toString());
            cue.setType(foundCue.getProperty("Type").toString());
            cue.setLocation(foundCue.getProperty("Location").toString());
            cue.setTime(foundCue.getProperty("Time").toString());
            cue.setEmotionalState(foundCue.getProperty("EmotionalState").toString());
            cue.setEnvironment(foundCue.getProperty("Environment").toString());
            cue.setAction(foundCue.getProperty("Action").toString());
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        return cue;
    }

    /**
     * loads a list of all cues for user in specified course
     * @param userKey user key in datastore
     * @param courseKey course key in datastore
     * @return array list of cue utility objects
     */
    public ArrayList<Cue> loadUserCues(String userKey, String courseKey, String type){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.Filter propertyFilter3 =
                new Query.FilterPredicate("Type", Query.FilterOperator.EQUAL, type);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2, propertyFilter3);
        Query cueQuery = new Query("Cue").setFilter(userCourseFilter);
        List<Entity> cueList =
                datastore.prepare(cueQuery).asList(FetchOptions.Builder.withDefaults());
        ArrayList<Cue> cues = new ArrayList<Cue>();
        for(Entity foundCue : cueList){
            Cue cue = new Cue();
            cue.setCueKey(KeyFactory.keyToString(foundCue.getKey()));
            cue.setUserKey(foundCue.getProperty("UserKey").toString());
            cue.setType(foundCue.getProperty("Type").toString());
            cue.setLocation(foundCue.getProperty("Location").toString());
            cue.setTime(foundCue.getProperty("Time").toString());
            cue.setEmotionalState(foundCue.getProperty("EmotionalState").toString());
            cue.setEnvironment(foundCue.getProperty("Environment").toString());
            cue.setAction(foundCue.getProperty("Action").toString());
            cues.add(cue);
        }
        return cues;
    }

    /**
     * adds a cue to datastore
     * @param cue cue trigger for a specific habit, object with properties
     */
    public void addCue(Cue cue) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity cue1 = new Entity("Cue");
            cue1.setProperty("UserKey", cue.getUserKey());
            cue1.setProperty("Type", cue.getType());
            cue1.setProperty("Location", cue.getLocation());
            cue1.setProperty("Time", cue.getTime());
            cue1.setProperty("EmotionalState", cue.getEmotionalState());
            cue1.setProperty("Environment", cue.getEnvironment());
            cue1.setProperty("Action", cue.getAction());
            datastore.put(txn, cue1);
            txn.commit();
            cue.setCueKey(KeyFactory.keyToString(cue1.getKey()));
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * updates cue in datastore
     * @param cue cue trigger for a habit, object with properties
     */
    public void editCue(Cue cue) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedCue = datastore.get(KeyFactory.stringToKey(cue.getCueKey()));
                updatedCue.setProperty("UserKey", cue.getUserKey());
                updatedCue.setProperty("Type", cue.getType());
                updatedCue.setProperty("Location", cue.getLocation());
                updatedCue.setProperty("Time", cue.getTime());
                updatedCue.setProperty("EmotionalState", cue.getEmotionalState());
                updatedCue.setProperty("Environment", cue.getEnvironment());
                updatedCue.setProperty("Action", cue.getAction());
                datastore.delete(KeyFactory.stringToKey(cue.getCueKey()));
                datastore.put(updatedCue);
                txn.commit();
                cue.setCueKey(KeyFactory.keyToString(updatedCue.getKey()));
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
     * Deletes cue from datastore
     * @param cueKey cue key
     */
    public void deleteCue(String cueKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(cueKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

}
