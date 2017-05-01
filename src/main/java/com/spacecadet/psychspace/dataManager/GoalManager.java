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
     * loads a single goal with matching user and course
     * @param courseKey key in course entity
     * @param userKey key in user entity
     * @return goal utility object
     */
    public Goal loadSingleGoal(String courseKey, String userKey){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query enrollQuery = new Query("Goal").setFilter(userCourseFilter);
        Entity foundGoal = datastore.prepare(enrollQuery).asSingleEntity();
        if (foundGoal == null) {
            return null;
        }
        Goal goal = new Goal();
        goal.setGoalName(foundGoal.getProperty("GoalName").toString());
        goal.setUserKey(foundGoal.getProperty("UserKay").toString());
        goal.setUsername(foundGoal.getProperty("UserName").toString());
        goal.setCourseKey(foundGoal.getProperty("CourseKey").toString());
        goal.setValue(foundGoal.getProperty("Value").toString());
        goal.setUnit(foundGoal.getProperty("Unit").toString());
        goal.setGoalKey(KeyFactory.keyToString(foundGoal.getKey()));
        return goal;
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
            goal1.setProperty("CourseKey", goal.getCourseKey());
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
                updatedGoal.setProperty("CourseKey", goal.getCourseKey());
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
}
