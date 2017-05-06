package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by aliao on 5/1/17.
 */
public class EvaluationManager {

    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

    /**
     * constructor
     */
    public EvaluationManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * load list of all past evaluations of user
     * @param userKey user key in datastore
     * @return array list of evaluation utility objects
     */
    public ArrayList<Evaluation> loadUserEvaluations(String courseKey, String userKey){
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("AuthorKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query evaluationQuery = new Query("Evaluation").setFilter(userCourseFilter);
        List<Entity> userEvaluations =
                datastore.prepare(evaluationQuery).asList(FetchOptions.Builder.withDefaults());
        ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
        for(Entity entity : userEvaluations){
            Evaluation evaluation = new Evaluation();
            evaluation.setAuthorKey(entity.getProperty("AuthorKey").toString());
            evaluation.setAuthor(entity.getProperty("Author").toString());
            evaluation.setContent(entity.getProperty("Content").toString());
            evaluation.setDate(entity.getProperty("Date").toString());
            evaluation.setCourseKey(entity.getProperty("CourseKey").toString());
            evaluation.setScore(entity.getProperty("Score").toString());
            evaluation.setRawScore(entity.getProperty("RawScore").toString());
            evaluation.setEvaluationKey(KeyFactory.keyToString(entity.getKey()));
            evaluations.add(evaluation);
        }
        return evaluations;
    }

    /**
     * chek if user submitted evalutaion for the day
     * @param userKey user key in datastore
     * @return true/false
     */
    public boolean hasTodaysEvaluation(String userKey){
        Date rawDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        String today = month + "/" + day + "/" + year;
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("AuthorKey", Query.FilterOperator.EQUAL, userKey);
        Query evaluationQuery = new Query("Evaluation").setFilter(propertyFilter1);
        List<Entity> userEvaluations =
                datastore.prepare(evaluationQuery).asList(FetchOptions.Builder.withDefaults());
        for(Entity entity : userEvaluations){
            String date = entity.getProperty("Date").toString();
            if(date.equals(today.toString())){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an evaluation entity to datastore
     * @param evaluation new evaluation entity
     */
    public void addEvaluation(Evaluation evaluation) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity entity = new Entity("Evaluation");
            entity.setProperty("AuthorKey", evaluation.getAuthorKey());
            entity.setProperty("CourseKey", evaluation.getCourseKey());
            entity.setProperty("Author", evaluation.getAuthor());
            entity.setProperty("Content", evaluation.getContent());
            entity.setProperty("Date", evaluation.getDate());
            entity.setProperty("RawScore", evaluation.getRawScore());
            entity.setProperty("Score", evaluation.getScore());
            datastore.put(txn, entity);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
}
