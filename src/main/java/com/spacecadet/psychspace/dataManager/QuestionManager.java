package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marleneshankar on 4/19/17.
 */
public class QuestionManager {
    private DatastoreService datastore;

    public QuestionManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public String[] parseQuestions(String questions) {
        String[] questionsList = questions.split("");
        return questionsList;
    }

    public String addQuestions(String surveyKey, String questions) {
        String keys = "";
        Transaction txn = datastore.beginTransaction();
        String[] questionsList = parseQuestions(questions);
        try {
            for (String question : questionsList) {
                Entity question1 = new Entity("Question");
                question1.setProperty("SurveyKey", surveyKey);
                question1.setProperty("Content", question);
                question1.setProperty("Type", "default");

                datastore.put(question1);
                txn.commit();
                keys.concat(" ");
                keys.concat(KeyFactory.keyToString(question1.getKey()));
            }
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
        return keys;
    }

    public ArrayList<Question> loadQuestions(String surveyKey) {
        Query.Filter surveyFilter =
                new Query.FilterPredicate("SurveyKey", Query.FilterOperator.EQUAL, surveyKey);
        Query questionQuery = new Query("Question").setFilter(surveyFilter);

        List<Entity> surveyQuestions =
                datastore.prepare(questionQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Question> questions = new ArrayList<>();
        for (Entity question : surveyQuestions) {
            Question question1 = new Question();
            question1.setSurveyKey(question.getProperty("SurveyKey").toString());
            question1.setQuestionKey(KeyFactory.keyToString(question.getKey()));
            question1.setContent(question.getProperty("Content").toString());
            question1.setType(question.getProperty("Type").toString());

            questions.add(question1);
        }
        return questions;
    }

    public void editQuestion() {
        Transaction txn = datastore.beginTransaction();

        try {

        } finally {
            if(txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public void deleteQuestion(String questionKey) {
        Transaction txn = datastore.beginTransaction();

        try {
            datastore.delete(KeyFactory.stringToKey(questionKey));
            txn.commit();

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
