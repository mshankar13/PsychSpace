package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Habit;

/**
 * Created by marleneshankar on 4/26/17.
 */
public class HabitManager {

    private DatastoreService datastore;

    /**
     * constructor for connecting to datastore
     */
    public HabitManager(){ datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     * create new habbit entity to datastore
     * @param habit new habbit
     */
    public void addHabit(Habit habit) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity habit1 = new Entity("Habit");
            habit1.setProperty("UserKey", habit.getUsername());
            habit1.setProperty("Username", habit.getUsername());
            habit1.setProperty("CueKey", habit.getCueKey());
            habit1.setProperty("GoalKey", habit.getGoalKey());
            habit1.setProperty("GoalName", habit.getGoalName());
            habit1.setProperty("Reward", habit.getReward());
            datastore.put(txn, habit1);
            txn.commit();
            habit.setHabitKey(KeyFactory.keyToString(habit1.getKey()));
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edit existing habbit in datastore
     * @param habit edited habbit
     */
    public void editHabit(Habit habit) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedHabit = datastore.get(KeyFactory.stringToKey(habit.getHabitKey()));
                updatedHabit.setProperty("UserKey", habit.getUsername());
                updatedHabit.setProperty("Username", habit.getUsername());
                updatedHabit.setProperty("CueKey", habit.getCueKey());
                updatedHabit.setProperty("GoalKey", habit.getGoalKey());
                updatedHabit.setProperty("GoalName", habit.getGoalName());
                updatedHabit.setProperty("Reward", habit.getReward());

                datastore.delete(KeyFactory.stringToKey(habit.getHabitKey()));
                datastore.put(updatedHabit);
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
     * delete habbit from datastore
     * @param habitKey key of the habbit
     */
    public void deleteHabit(String habitKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(habitKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

}
