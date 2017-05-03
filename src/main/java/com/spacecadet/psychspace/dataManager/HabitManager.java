package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.DueDates;
import com.spacecadet.psychspace.utilities.Habit;

/**
 * Created by marleneshankar on 4/26/17.
 * modified by aliao on 5/3/17.
 */
public class HabitManager {

    private DatastoreService datastore;

    /**
     * constructor for connecting to datastore
     */
    public HabitManager(){ datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     * load a single habit from datastore by habit key
     * @param habitKey habit key in datastore
     * @return habit utility object
     */
    public Habit loadSingleHabit(String habitKey){
        Habit habit = new Habit();
        try {
            Entity foundHabit = datastore.get(KeyFactory.stringToKey(habitKey));
            habit.setHabitKey(KeyFactory.keyToString(foundHabit.getKey()));
            habit.setCourseKey(foundHabit.getProperty("CourseKey").toString());
            habit.setUserKey(foundHabit.getProperty("UserKey").toString());
            habit.setUsername(foundHabit.getProperty("UserName").toString());
            habit.setCueKey(foundHabit.getProperty("CueKey").toString());
            habit.setGoalKey(foundHabit.getProperty("GoalKey").toString());
            habit.setReward(foundHabit.getProperty("Reward").toString());
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        return habit;
    }

    /**
     * load a single habit from datastore by user key
     * @param userKey user key in datastore
     * @return habit utility object
     */
    public Habit loadUserHabit(String userKey, String courseKey){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query dueDatesQuery = new Query("Habit").setFilter(userCourseFilter);
        Entity foundHabit = datastore.prepare(dueDatesQuery).asSingleEntity();
        if(foundHabit == null){
            return null;
        }
        Habit habit = new Habit();
        habit.setHabitKey(KeyFactory.keyToString(foundHabit.getKey()));
        habit.setCourseKey(foundHabit.getProperty("CourseKey").toString());
        habit.setUserKey(foundHabit.getProperty("UserKey").toString());
        habit.setUsername(foundHabit.getProperty("UserName").toString());
        habit.setCueKey(foundHabit.getProperty("CueKey").toString());
        habit.setGoalKey(foundHabit.getProperty("GoalKey").toString());
        habit.setReward(foundHabit.getProperty("Reward").toString());
        return habit;
    }

    /**
     * create new habbit entity to datastore
     * @param habit new habbit
     */
    public void addHabit(Habit habit) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity habit1 = new Entity("Habit");
            habit1.setProperty("CourseKey", habit.getCourseKey());
            habit1.setProperty("UserKey", habit.getUsername());
            habit1.setProperty("Username", habit.getUsername());
            habit1.setProperty("CueKey", habit.getCueKey());
            habit1.setProperty("GoalKey", habit.getGoalKey());
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
                updatedHabit.setProperty("CoruseKey", habit.getCourseKey());
                updatedHabit.setProperty("UserKey", habit.getUsername());
                updatedHabit.setProperty("Username", habit.getUsername());
                updatedHabit.setProperty("CueKey", habit.getCueKey());
                updatedHabit.setProperty("GoalKey", habit.getGoalKey());
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
}
