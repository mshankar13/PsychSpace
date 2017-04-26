package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.controller.WelcomeController;
import com.spacecadet.psychspace.utilities.User;

/**
 * Created by marleneshankar on 3/24/17.
 * Modeified by aliao on 4/21/17.
 */
public class UserManager {
    private DatastoreService datastore;

    /**
     * constructor
     */
    public UserManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * add new user to datastore
     * @param user new user object
     * @param role user/instructor/admin
     * @return user object
     */
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
     * @param email user email
     * @return user object found else null
     */
    public User accExists(String email) {
        datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter userFilter =
                new Query.FilterPredicate("Email", Query.FilterOperator.EQUAL, email);
        Query userQuery = new Query("User").setFilter(userFilter);
        Entity foundUser = datastore.prepare(userQuery).asSingleEntity();
        if (foundUser == null) {
            return null;
        } else {
            User user = new User();
            user.setUserKey(KeyFactory.keyToString(foundUser.getKey()));
            user.setEmail(email);
            user.setFirstName(foundUser.getProperty("FirstName").toString());
            user.setLastName(foundUser.getProperty("LastName").toString());
            user.setRole(foundUser.getProperty("Role").toString());
            return user;
        }
    }

    /**
     * Checks to see if an email is already registered with an account
     * @param email user email
     * @return email is/not already linked to an account
     */
    public User emailRegistered(String email) {
        return accExists(email);
    }


    /**
     * helper method for reseting current user
     * @param user user object
     */
    public void resetCurrentUser(User user){
        WelcomeController.currUser.setFirstName(user.getFirstName());
        WelcomeController.currUser.setRole(user.getRole());
        WelcomeController.currUser.setUserKey(user.getUserKey());
        WelcomeController.currUser.setEmail(user.getEmail());
        WelcomeController.currUser.setLastName(user.getLastName());
    }

    /**
     * load single user object by userkey
     * @param userKey user key
     * @return user object
     */
    public User loadSingleUser(String userKey){
        User user = new User();
        try {
            Entity singleUser = datastore.get(KeyFactory.stringToKey(userKey));
            user.setUserKey(userKey);
            user.setEmail(singleUser.getProperty("Email").toString());
            user.setFirstName(singleUser.getProperty("FirstName").toString());
            user.setLastName(singleUser.getProperty("LastName").toString());
            user.setRole(singleUser.getProperty("Role").toString());
        } catch (Exception ex) {
            return null;
        }
        return user;
    }
}
