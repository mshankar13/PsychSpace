package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.User;

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

    public User addUser(User user, String role) {

        Transaction txn = datastore.beginTransaction();
        datastore = DatastoreServiceFactory.getDatastoreService();
        Entity userEntity = new Entity("User", user.getEmail());

        try {

            userEntity.setProperty("Email", user.getEmail());
            userEntity.setProperty("FirstName", user.getFirstName());
            userEntity.setProperty("LastName", user.getLastName());
            userEntity.setProperty("Role", role);
            datastore.put(txn, userEntity);

            txn.commit();

            try {
                datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.get(userEntity.getKey());
            } catch (EntityNotFoundException ex) {
                System.out.println("User was not added successfully");
            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
            user.setRole(role);
            user.setUserKey(KeyFactory.keyToString(userEntity.getKey()));
            return user;
        }

    }

    /**
     * Check to see if the email is already linked to an existing account and either returns an object or null
     *
     * @param email
     * @return foundUser or null
     */
    public String accExists(String email) {
        datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter userFilter =
                new Query.FilterPredicate("Email", Query.FilterOperator.EQUAL, email);
        Query userQuery = new Query("User").setFilter(userFilter);
        Entity foundUser = datastore.prepare(userQuery).asSingleEntity();

        if (foundUser == null) {
            return null;
        }

        return KeyFactory.keyToString(foundUser.getKey());
    }

    /**
     * Checks to see if an email is already registered with an account
     *
     * @param email
     * @return true - the email is already linked to an account
     */
    public String emailRegistered(String email) {
        return accExists(email);
    }

}
