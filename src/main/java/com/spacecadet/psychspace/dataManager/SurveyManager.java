package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Survey;

/**
 * Created by marleneshankar on 4/14/17.
 */
public class SurveyManager {
    private DatastoreService datastore;
    public SurveyManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public Survey loadSurvey(String userKey, String courseKey) {
        Survey survey = new Survey();

        return survey;
    }
    public void addSurvey(String userKey, String courseKey, String title, String dueDate) {
        Transaction txn = datastore.beginTransaction();

        try {
            Entity survey = new Entity("Survey");
            survey.setProperty("UserKey", userKey);
            survey.setProperty("CourseKey", courseKey);
            survey.setProperty("Title", title);
            survey.setProperty("DueDate", dueDate);
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
    public void editSurvey(String surveyKey, String title, String dueDate) {
        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedNews = datastore.get(KeyFactory.stringToKey(surveyKey));
                updatedNews.setProperty("Title", title);
                updatedNews.setProperty("DueDate", dueDate);

                datastore.delete(KeyFactory.stringToKey(surveyKey));
                datastore.put(updatedNews);
                txn.commit();

            } catch (EntityNotFoundException ex) {

            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
    public void deleteSurvey(String surveyKey) {
        Transaction txn = datastore.beginTransaction();

        try {
            datastore.delete(KeyFactory.stringToKey(surveyKey));
            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
