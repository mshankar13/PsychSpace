package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.DueDates;

/**
 * Created by aliao on 5/3/2017.
 */
public class DueDatesManager {


    private DatastoreService datastore;

    /**
     * constructor for connecting to datastore
     */
    public DueDatesManager(){ datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     * load single due dates entity for a specified course
     * @param courseKey course key in datastore
     * @return due dates utility object
     */
    public DueDates loadDueDatesForCourse(String courseKey){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query dueDatesQuery = new Query("DueDate").setFilter(propertyFilter1);
        Entity foundDueDates = datastore.prepare(dueDatesQuery).asSingleEntity();
        if(foundDueDates == null){
            return null;
        }
        DueDates dueDates = new DueDates();
        dueDates.setDueDatesKey(KeyFactory.keyToString(foundDueDates.getKey()));
        dueDates.setCourseKey(foundDueDates.getProperty("CourseKey").toString());
        dueDates.setCuesDueDate(foundDueDates.getProperty("CuesDueDate").toString());
        dueDates.setGoalDueDate(foundDueDates.getProperty("GoalDueDate").toString());
        dueDates.setHabitDueDate(foundDueDates.getProperty("HabitDueDate").toString());
        return dueDates;
    }

    /**
     * create new dueDate entity to datastore
     * @param dueDates new due dates
     */
    public void addDueDate(DueDates dueDates) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity entity = new Entity("DueDate");
            entity.setProperty("CourseKey", dueDates.getCourseKey());
            entity.setProperty("GoalDueDate", dueDates.getGoalDueDate());
            entity.setProperty("CuesDueDate", dueDates.getCuesDueDate());
            entity.setProperty("HabitDueDate", dueDates.getHabitDueDate());
            datastore.put(txn, entity);
            txn.commit();
            dueDates.setDueDatesKey(KeyFactory.keyToString(entity.getKey()));
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edit existing due dates in datastore
     * @param dueDates edited due dates
     */
    public void editDueDates(DueDates dueDates) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedEntity = datastore.get(KeyFactory.stringToKey(dueDates.getDueDatesKey()));
                updatedEntity.setProperty("CourseKey", dueDates.getCourseKey());
                updatedEntity.setProperty("GoalDueDate", dueDates.getGoalDueDate());
                updatedEntity.setProperty("CuesDueDate", dueDates.getCuesDueDate());
                updatedEntity.setProperty("HabitDueDate", dueDates.getHabitDueDate());
                datastore.delete(KeyFactory.stringToKey(dueDates.getDueDatesKey()));
                datastore.put(updatedEntity);
                txn.commit();
                dueDates.setDueDatesKey(KeyFactory.keyToString(updatedEntity.getKey()));
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
