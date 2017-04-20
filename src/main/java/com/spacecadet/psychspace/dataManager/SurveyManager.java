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
    public void addSurvey(Survey survey) {
        Transaction txn = datastore.beginTransaction();

        try {
            Entity survey1 = new Entity("Survey");
            survey1.setProperty("UserKey", survey.getUserKey());
            survey1.setProperty("CourseKey", survey.getCourseKey());
            survey1.setProperty("Title", survey.getTitle());
            survey1.setProperty("DueDate", survey.getDueDate());
            survey.setSurveyKey(KeyFactory.keyToString(survey1.getKey()));
            datastore.put(txn, survey1);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }

        }
    }
    
    public void editSurvey(Survey survey) {
        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedSurvey = datastore.get(KeyFactory.stringToKey(survey.getSurveyKey()));
                updatedSurvey.setProperty("Title", survey.getTitle());
                updatedSurvey.setProperty("DueDate", survey.getDueDate());

                datastore.delete(KeyFactory.stringToKey(survey.getSurveyKey()));
                datastore.put(updatedSurvey);
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
