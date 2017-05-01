package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Goal;

/**
 * Created by marleneshankar on 4/26/17.
 */
public class GoalManager {

    private DatastoreService datastore;

    /** constructor */
    public GoalManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * adds a goal to the datastore
     * @param goal goal object with properties
     */
    public void addGoal(Goal goal) {

        Transaction txn = datastore.beginTransaction();
        try {

            Entity goal1 = new Entity("Goal");
            goal1.setProperty("GoalName", goal.getGoalName());
            goal1.setProperty("Username", goal.getUsername());
            goal1.setProperty("UserKey", goal.getUserKey());
            goal1.setProperty("Value", goal.getValue());
            goal1.setProperty("Unit", goal.getUnit());
            datastore.put(txn, goal1);
            txn.commit();

            goal.setGoalKey(KeyFactory.keyToString(goal1.getKey()));
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edits a goal and updates datastore
     * @param goal goal object with updated properties
     */
    public void editGoal(Goal goal) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedGoal = datastore.get(KeyFactory.stringToKey(goal.getGoalKey()));
                updatedGoal.setProperty("GoalName", goal.getGoalName());
                updatedGoal.setProperty("Username", goal.getUsername());
                updatedGoal.setProperty("UserKey", goal.getUserKey());
                updatedGoal.setProperty("Value", goal.getValue());
                updatedGoal.setProperty("Unit", goal.getUnit());

                datastore.delete(KeyFactory.stringToKey(goal.getGoalKey()));
                datastore.put(updatedGoal);
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
     * deletes a goal from datastore
     * @param goalKey goal key
     */
    public void deleteGoal(String goalKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(goalKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

}
