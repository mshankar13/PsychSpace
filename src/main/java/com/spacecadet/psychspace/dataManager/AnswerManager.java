package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Answer;

import java.util.ArrayList;

/**
 * Created by marleneshankar on 4/19/17.
 */
public class AnswerManager {
    private DatastoreService datastore;

    public AnswerManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public void addAnswers(String questionKey, ArrayList<Answer> answers) {
        Transaction txn = datastore.beginTransaction();

        try {
            for (Answer answer : answers) {
                Entity answer1 = new Entity("Answer");
                answer1.setProperty("Answer", answer.getAnswer());
                answer1.setProperty("Type", answer.getScore());
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
