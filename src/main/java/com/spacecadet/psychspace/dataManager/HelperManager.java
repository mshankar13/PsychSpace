package com.spacecadet.psychspace.dataManager;


import com.google.appengine.repackaged.com.google.gson.Gson;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.News;
import com.spacecadet.psychspace.utilities.User;
import com.google.appengine.repackaged.com.google.gson.*;
import com.spacecadet.psychspace.utilities.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by marleneshankar on 4/8/17.
 */
public class HelperManager {

    /**
     * helper for converting string to json
     * @param str original string
     * @param objectType utility object type
     * @return java utility object
     */
    public Object stringToJson(String str, String objectType) {
        Gson g = new Gson();
        if (objectType.compareTo("User") == 0) {
            return g.fromJson(str, User.class);
        } else if (objectType.compareTo("News") == 0) {
            return g.fromJson(str, News.class);
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
        DateFormat df2 = new SimpleDateFormat("mm/dd/yyyy");
        Date date = new Date();
        try {
            date = df2.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public Survey surveyStringToJson(String str) {
        Survey survey = new Survey();

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(str).getAsJsonObject();

        JsonElement courseKey = o.get("course");
        survey.setCourseKey(courseKey.toString());
        JsonElement title = o.get("title");
        survey.setTitle(title.toString());

        JsonObject questions = o.getAsJsonObject("questions");
        JsonElement questionTotal = o.get("questionTotal");

        HashMap<Question, ArrayList<Answer>> questionsMap = new HashMap<>();

        int index = 0;
        while (index < Integer.parseInt(questionTotal.toString())) {
            JsonObject question = questions.getAsJsonObject(Integer.toString(index));

            Question question1 = new Question();
            question1.setContent(question.get("question").toString());
            question1.setType(question.get("type").toString());

            JsonObject answers = question.getAsJsonObject("answers");
            JsonElement answerTotal = question.get("answerTotal");
            int index1 = 0;

            ArrayList<Answer> answerList = new ArrayList<>();
            while (index1 < Integer.parseInt(answerTotal.toString())) {
                JsonObject answer = answers.getAsJsonObject(Integer.toString(index1));

                Answer answer1 = new Answer();
                answer1.setAnswer(answer.get("answer").toString());
                answer1.setScore(answer.get("score").toString());

                answerList.add(answer1);
                index1++;
            }
            questionsMap.put(question1, answerList);
            index++;
        }

        survey.setQuestions(questionsMap);
        return survey;
    }
}
