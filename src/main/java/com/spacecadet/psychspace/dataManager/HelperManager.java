package com.spacecadet.psychspace.dataManager;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Article;
import com.spacecadet.psychspace.utilities.User;
import com.google.appengine.repackaged.com.google.gson.*;
import com.spacecadet.psychspace.utilities.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by marleneshankar on 4/8/17.
 * modified by aliao on 5/1/17.
 */
public class HelperManager {

    /**
     * helper for converting string to json
     * @param str        original string
     * @param objectType utility object type
     * @return java utility object
     */
    public Object stringToJson(String str, String objectType) {
        Gson g = new Gson();
        if (objectType.compareTo("User") == 0) {
            return g.fromJson(str, User.class);
        } else if (objectType.compareTo("Article") == 0) {
            return g.fromJson(str, Article.class);
        } else if (objectType.compareTo("Comment") == 0) {
            return g.fromJson(str, Comment.class);
        }
        return null;
    }

    /**
     * helper for converting string to json list
     * @param str string of all keys
     * @return json list of news keys
     */
    public String[] stringToJsonNewsKeyList(String str) {
        Gson g = new Gson();
        return g.fromJson(str, String[].class);
    }

    /**
     * helper for converting string to date objects
     * @param dateString string of date
     * @return java date object
     */
    public Date stringToDate(String dateString) {
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        try {
            date = df2.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * deletes an entity from datastore given the key
     * @param entityKey entity key
     */
    public void deleteEntity(String entityKey) {

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(entityKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
    /**
     * helper for converting survey from string to survey object
     * @param str survey in json string
     * @return survey in survey utility object
     */
    public Survey surveyStringToSurvey(String str) {
        Survey survey = new Survey();

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(str).getAsJsonObject();

        JsonElement courseKey = o.get("courseKey");
        survey.setCourseKey(courseKey.toString().replaceAll("^\"|\"$", ""));
        JsonElement courseTitle = o.get("courseTitle");
        survey.setCourseTitle(courseTitle.toString().replaceAll("^\"|\"$", ""));
        JsonElement title = o.get("title");
        survey.setTitle(title.toString().replaceAll("^\"|\"$", ""));
        JsonElement date = o.get("date");
        survey.setDueDate(date.toString().replaceAll("^\"|\"$", ""));

        JsonObject questions = o.getAsJsonObject("questions");
        JsonElement questionTotal = o.get("questionTotal");

        HashMap<Question, ArrayList<Answer>> questionsMap = new HashMap<>();

        int index = 0;
        // get questions
        while (index < Integer.parseInt(questionTotal.toString())) {
            JsonObject question = questions.getAsJsonObject(Integer.toString(index).replaceAll("^\"|\"$", ""));

            Question question1 = new Question();
            question1.setContent(question.get("question").toString().replaceAll("^\"|\"$", ""));
            question1.setType(question.get("type").toString().replaceAll("^\"|\"$", ""));

            JsonObject answers = question.getAsJsonObject("answers");
            JsonElement answerTotal = question.get("answerTotal");
            int index1 = 0;

            ArrayList<Answer> answerList = new ArrayList<>();
            // get answers
            while (index1 < Integer.parseInt(answerTotal.toString().replaceAll("^\"|\"$", ""))) {
                JsonObject answer = answers.getAsJsonObject(Integer.toString(index1).replaceAll("^\"|\"$", ""));

                Answer answer1 = new Answer();
                answer1.setAnswer(answer.get("answer").toString().replaceAll("^\"|\"$", ""));
                answer1.setScore(answer.get("score").toString().replaceAll("^\"|\"$", ""));

                answerList.add(answer1);
                index1++;
            }
            questionsMap.put(question1, answerList);
            index++;
        }

        survey.setQuestions(questionsMap);
        return survey;
    }

    /**
     * helper for converting survey from object to string
     * @param survey survey in survey utility object
     * @return sruvey in json string
     */
    public String surveyObjectsToJsonString(Survey survey) {
        Gson gson = new Gson();
        JsonObject res = new JsonObject();
        JsonObject survey1 = new JsonObject();
        survey1.add("Properties", gson.toJsonTree(survey));
        JsonObject questions = new JsonObject();
        int i = 0;

        // convert questions
        for (Question question : survey.getQuestions().keySet()) {
            JsonObject question1 = new JsonObject();
            JsonObject answers = new JsonObject();
            int index = 0;

            // convert corresponding answers
            for (Answer answer : survey.getQuestions().get(question)) {
                answers.add(Integer.toString(index), gson.toJsonTree(answer));
                index++;
            }
            question1.add("AnswerTotal", gson.toJsonTree(Integer.toString(index)));
            question1.add("QuestionProperties", gson.toJsonTree(question));
            question1.add("Answers", gson.toJsonTree(answers));
            questions.add(Integer.toString(i), gson.toJsonTree(question1));
            i++;
        }
        survey1.add("Questions", gson.toJsonTree(questions));
        survey1.add("QuestionTotal", gson.toJsonTree(Integer.toString(i)));

        return survey1.toString();
    }

    /**
     * helper method to shorten the course title and descriptions
     * @param courses list of courses
     * @return list of courses with shortened content
     */
    public ArrayList<Course> shortenCourseList(ArrayList<Course> courses){
        for(Course c : courses){
            if(c.getTitle().length() >= 50){
                c.setTitle(c.getTitle().substring(0, 50));
            }
            if(c.getDescription().length() >= 100){
                c.setDescription(c.getDescription().substring(0, 100));
            }
        }
        return courses;
    }

}
