package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;

/**
 * Created by marleneshankar on 4/14/17.
 */
public class EnrollManager {
    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

    public EnrollManager() { datastore = DatastoreServiceFactory.getDatastoreService(); }

    public void enroll(String userKey, String courseKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity enroll = new Entity("Enroll");
            enroll.setProperty("UserKey", userKey);
            enroll.setProperty("CourseKey", courseKey);

            datastore.put(txn, enroll);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public void unenroll(String userKey, String courseKey) {
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query enrollQuery = new Query("Enroll").setFilter(propertyFilter1).setFilter(propertyFilter2);
        Entity foundEnrolled = datastore.prepare(enrollQuery).asSingleEntity();

        Transaction txn = datastore.beginTransaction();

        try {
            datastore.delete(foundEnrolled.getKey());
            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
