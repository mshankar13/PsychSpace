package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Cue;

/**
 * Created by marleneshankar on 4/26/17.
 */
public class CueManager {

    private DatastoreService datastore;

    /** constructor */
    public CueManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
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
