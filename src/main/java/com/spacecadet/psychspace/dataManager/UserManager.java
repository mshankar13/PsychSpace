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

    public void addUser(String email, String password, String firstName, String lastName, Date dob,
                        String role) {

        Transaction txn = datastore.beginTransaction();
        datastore = DatastoreServiceFactory.getDatastoreService();
        try {

            Entity user = new Entity("User", email);
            user.setProperty("Email", email);
            user.setProperty("Password", password);
            user.setProperty("FirstName", firstName);
            user.setProperty("LastName", lastName);
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
            verifyUser(email, password);
        }

    }

    /**
     * Check to see if the email is already linked to an existing account and either returns an object or null
     *
     * @param email
     * @return foundUser or null
     */
    public Entity accExists(String email) {
        datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter userFilter =
                new Query.FilterPredicate("Email", Query.FilterOperator.EQUAL, email);
        Query userQuery = new Query("User").setFilter(userFilter);
        Entity foundUser = datastore.prepare(userQuery).asSingleEntity();

        if (foundUser == null) {
            return null;
        }
        return foundUser;
    }

    /**
     * Checks to see if an email is already registered with an account
     *
     * @param email
     * @return true - the email is already linked to an account
     */
    public boolean emailRegistered(String email) {
        if (accExists(email) == null) {
            return false;
        }
        return true;
    }

    /**
     * verify user login username and password
     *
     * @param email
     * @param password
     * @return
     */
    public boolean verifyUser(String email, String password) {
        Entity foundUser = accExists(email);
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
