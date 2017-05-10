package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Answer;
import com.spacecadet.psychspace.utilities.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marleneshankar on 4/19/17.
 */
public class QuestionManager {
    private DatastoreService datastore;
    private AnswerManager answerManager = new AnswerManager();

    /** constructor */
    public QuestionManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * adds corresponding survey questions to datastore
     * @param surveyKey survey key
     * @param questionMap hashmap of questions with corresponding list of answers
     */
    public void addQuestions(String surveyKey, HashMap<Question, ArrayList<Answer>> questionMap) {
        for (Question question : questionMap.keySet()) {
            Entity question1 = new Entity("Question");
            question1.setProperty("Content", question.getContent());
            question1.setProperty("Type", question.getType());
            question1.setProperty("SurveyKey", surveyKey);
            datastore.put(question1);

            String k = KeyFactory.keyToString(question1.getKey());
            ArrayList<Answer> a = questionMap.get(question);
            answerManager.addAnswers(k, a);
        }
    }

    /**
     * loads the questions corresponding to a survey
     * @param surveyKey survey key
     * @return
     */
    public HashMap<Question, ArrayList<Answer>> loadQuestions(String surveyKey) {

        Query.Filter questionFilter =
                new Query.FilterPredicate("SurveyKey", Query.FilterOperator.EQUAL, surveyKey);
        Query questionQuery = new Query("Question").setFilter(questionFilter);

        List<Entity> surveyQuestions =
                datastore.prepare(questionQuery).asList(FetchOptions.Builder.withDefaults());

        HashMap<Question, ArrayList<Answer>> questions = new HashMap<>();
        for (Entity question : surveyQuestions) {
            Question question1 = new Question();
            question1.setSurveyKey(question.getProperty("SurveyKey").toString());
            question1.setQuestionKey(KeyFactory.keyToString(question.getKey()));
            question1.setContent(question.getProperty("Content").toString());
            question1.setType(question.getProperty("Type").toString());

            String key = KeyFactory.keyToString(question.getKey());
            ArrayList<Answer> answers = answerManager.loadAnswers(key);
            questions.put(question1, answers);
        }
        return questions;
    }

    public void editQuestion(String surveyKey, HashMap<Question, ArrayList<Answer>> questionMap) {

    }

    /**
     * deletes a question from datastore
     * @param surveyKey survey key in datastore
     */
    public void deleteQuestion(String surveyKey) {

        Query.Filter questionFilter =
                new Query.FilterPredicate("SurveyKey", Query.FilterOperator.EQUAL, surveyKey);
        Query questionQuery = new Query("Question").setFilter(questionFilter);
        List<Entity> surveyQuestions =
                datastore.prepare(questionQuery).asList(FetchOptions.Builder.withDefaults());

        for (Entity question : surveyQuestions) {
            String key = KeyFactory.keyToString(question.getKey());
            Query.Filter answerFilter =
                    new Query.FilterPredicate("QuestionKey", Query.FilterOperator.EQUAL, key);
            Query answerQuery = new Query("Question").setFilter(answerFilter);
            List<Entity> questionAnswers =
                    datastore.prepare(answerQuery).asList(FetchOptions.Builder.withDefaults());
            for (Entity answer : questionAnswers) {
                datastore.delete(answer.getKey());
            }
            datastore.delete(question.getKey());
        }
    }
}