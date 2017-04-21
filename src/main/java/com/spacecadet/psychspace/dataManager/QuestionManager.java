package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Answer;
import com.spacecadet.psychspace.utilities.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marleneshankar on 4/19/17.
 */
public class QuestionManager {
    private DatastoreService datastore;
    private AnswerManager answerManager = new AnswerManager();

    public QuestionManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }


    public void addQuestions(String surveyKey, HashMap<Question, ArrayList<Answer>> questionMap) {
        Transaction txn = datastore.beginTransaction();

        try {
            for (Question question : questionMap.keySet()) {
                Entity question1 = new Entity("Question");
                question1.setProperty("Content", question.getContent());
                question1.setProperty("Type", question.getType());
                question1.setProperty("surveyKey", surveyKey);
                datastore.put(txn, question1);
                txn.commit();

                answerManager.addAnswers(KeyFactory.keyToString(question1.getKey()), questionMap.get(question));
            }
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public ArrayList<Question> loadQuestions(String surveyKey) {
        Query.Filter surveyFilter =
                new Query.FilterPredicate("SurveyKey", Query.FilterOperator.EQUAL, surveyKey);
        Query questionQuery = new Query("Question").setFilter(surveyFilter);

        List<Entity> surveyQuestions =
                datastore.prepare(questionQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Question> questions = new ArrayList<>();
        for (Entity question : surveyQuestions) {

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
