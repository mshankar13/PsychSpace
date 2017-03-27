package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;

import java.util.Date;

/**
 * Created by marleneshankar on 3/24/17.
 */
public class UserManager {
    private DatastoreService datastore;
    public static Key currentUserID;
    public UserManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public void addUser (String username, String password, String firstName, String lastName, String email, Date dob,
                         String role) {

        Transaction txn = datastore.beginTransaction();
        datastore = DatastoreServiceFactory.getDatastoreService();
        try {

            Entity user = new Entity("User", username);
            user.setProperty("Username", username);
            user.setProperty("Password", password);
            user.setProperty("FirstName", firstName);
            user.setProperty("LastName", lastName);
            user.setProperty("Email", email);
            user.setProperty("DateOfBirth", dob);
            user.setProperty("Role", role);
            datastore.put(txn, user);

            try {
                datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.get(user.getKey());
            } catch (EntityNotFoundException ex) {
                ex.printStackTrace();
            }

            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
            verifyUser(username, password);
        }

    }

    /**
     * verify user login username and password
     * @param username
     * @param password
     * @return
     */
    public boolean verifyUser (String username, String password) {

        /* Query for public lists using public query filter */
        datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter userFilter =
                new Query.FilterPredicate("Username", Query.FilterOperator.EQUAL, username);
        Query userQuery = new Query("User").setFilter(userFilter);
        Entity foundUser = datastore.prepare(userQuery).asSingleEntity();

        if (foundUser == null) {
            return false;
        } else {
            /* Verify password */
            if (password.equals(foundUser.getProperty("Password").toString())) {
                currentUserID = foundUser.getKey();
                return true;
            }
        }
        return false;
    }
}
