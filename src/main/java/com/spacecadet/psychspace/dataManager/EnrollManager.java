package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Course;

/**
 * Created by aliao on 4/19/17.
 */
public class EnrollManager {

    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * constructor
     */
    public EnrollManager() { datastore = DatastoreServiceFactory.getDatastoreService(); }

    /**
     * add new enroll entity to datastore
     * @param userKey  user key
     * @param courseKey course key
     */
    public void enroll(String userKey, String courseKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity enroll = new Entity("Enroll");
            enroll.setProperty("UserKey", userKey);
            enroll.setProperty("CourseKey", courseKey);

            datastore.put(txn, enroll);
            txn.commit();

            Course course = courseManager.loadSingleCourse(courseKey);
            course.setCurrSize(Integer.toString(Integer.parseInt(course.getCurrSize())+1));
            courseManager.editCourse(course);
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * update enroll entity in datastore
     * @param userKey user key
     * @param courseKey course key
     */
    public void unenroll(String userKey, String courseKey) {
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query enrollQuery = new Query("Enroll").setFilter(userCourseFilter);
        Entity foundEnrolled = datastore.prepare(enrollQuery).asSingleEntity();
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(foundEnrolled.getKey());
            txn.commit();
            Course course = courseManager.loadSingleCourse(courseKey);
            course.setCurrSize(Integer.toString(Integer.parseInt(course.getCurrSize())-1));
            courseManager.editCourse(course);
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * check if user is enrolled to course
     * @param userKey user key
     * @param courseKey course key
     * @return is/not enrolled
     */
    public boolean isEnrolled(String userKey, String courseKey){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query enrollQuery = new Query("Enroll").setFilter(userCourseFilter);
        Entity foundEnrolled = datastore.prepare(enrollQuery).asSingleEntity();
        if(foundEnrolled == null){
            return false;
        }
        return true;
    }
}
