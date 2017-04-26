package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Answer;
import com.spacecadet.psychspace.utilities.Question;
import com.spacecadet.psychspace.utilities.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marleneshankar on 4/14/17.
 */
public class SurveyManager {
    private DatastoreService datastore;
    private QuestionManager questionManager = new QuestionManager();

    public SurveyManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public ArrayList<Survey> loadSurveys(String userKey) {

        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query surveyQuery = new Query("Survey").setFilter(propertyFilter1);

        List<Entity> surveyList =
                datastore.prepare(surveyQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList <Survey> surveys = new ArrayList<>();

        for (Entity survey : surveyList) {
            Survey survey1 = new Survey();
            survey1.setSurveyKey(KeyFactory.keyToString(survey.getKey()));
            survey1.setCourseKey(survey.getProperty("CourseKey").toString());
            survey1.setUserKey(userKey);
            survey1.setTitle(survey.getProperty("Title").toString());
            survey1.setDueDate(survey.getProperty("DueDate").toString());

            String key = KeyFactory.keyToString(survey.getKey());
            HashMap<Question, ArrayList<Answer>> questions = questionManager.loadQuestions(key);
            survey1.setQuestions(questions);
            surveys.add(survey1);
        }

        return surveys;
    }
    public void addSurvey(Survey survey) {
        Transaction txn = datastore.beginTransaction();

        try {
            Entity survey1 = new Entity("Survey");
            survey1.setProperty("UserKey", survey.getUserKey());
            survey1.setProperty("CourseKey", survey.getCourseKey());
            survey1.setProperty("Title", survey.getTitle());
            survey1.setProperty("DueDate", survey.getDueDate());
            datastore.put(txn, survey1);
            txn.commit();

            survey.setSurveyKey(KeyFactory.keyToString(survey1.getKey()));
            questionManager.addQuestions(survey.getSurveyKey(), survey.getQuestions());
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
