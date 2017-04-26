package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Answer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marleneshankar on 4/19/17.
 */
public class AnswerManager {
    private DatastoreService datastore;

    public AnswerManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public ArrayList<Answer> loadAnswers(String questionKey) {

        Query.Filter surveyFilter =
                new Query.FilterPredicate("QuestionKey", Query.FilterOperator.EQUAL, questionKey);
        Query answerQuery = new Query("Answer").setFilter(surveyFilter);

        List<Entity> questionAnswers =
                datastore.prepare(answerQuery).asList(FetchOptions.Builder.withDefaults());

        ArrayList<Answer> answers = new ArrayList<>();
        for (Entity answer : questionAnswers) {
            Answer answer1 = new Answer();
            answer1.setQuestionKey(questionKey);
            answer1.setAnswer(answer.getProperty("Answer").toString());
            answer1.setScore(answer.getProperty("Score").toString());

            answers.add(answer1);
        }

        return answers;
    }
    public void addAnswers(String questionKey, ArrayList<Answer> answers) {
        Transaction txn = datastore.beginTransaction();

        try {
            for (Answer answer : answers) {
                if (txn.isActive()) {
                    txn.rollback();
                }
                txn = datastore.beginTransaction();
                Entity answer1 = new Entity("Answer");
                answer1.setProperty("Answer", answer.getAnswer());
                answer1.setProperty("Score", answer.getScore());
                answer1.setProperty("QuestionKey", questionKey);

                datastore.put(txn, answer1);
                txn.commit();
            }
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
