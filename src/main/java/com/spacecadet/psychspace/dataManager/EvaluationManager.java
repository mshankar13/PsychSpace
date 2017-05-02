package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.User;

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
     * load user submitted evalutaion for the day
     * @param userKey user key in datastore
     * @return evaluation entity
     */
    public Evaluation loadTodaysEvaluation(String userKey){
        Evaluation evaluation = new Evaluation();
        Date today = new Date();
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("AuthorKey", Query.FilterOperator.EQUAL, userKey);
        Query evaluationQuery = new Query("Evaluation").setFilter(propertyFilter1);
        List<Entity> userEvaluations =
                datastore.prepare(evaluationQuery).asList(FetchOptions.Builder.withDefaults());
        for(Entity entity : userEvaluations){
            Date date = helper.stringToDate(entity.getProperty("Date").toString());
            if(date.equals(today)){
                evaluation.setAuthorKey(entity.getProperty("AuthorKey").toString());
                evaluation.setAuthor(entity.getProperty("Author").toString());
                evaluation.setCourseKey(entity.getProperty("CourseKey").toString());
                evaluation.setContent(entity.getProperty("Content").toString());
                evaluation.setDate(entity.getProperty("Date").toString());
                evaluation.setRawScore(entity.getProperty("RawScore").toString());
                evaluation.setScore(entity.getProperty("Score").toString());
                evaluation.setEvaluationKey(KeyFactory.keyToString(entity.getKey()));
                return evaluation;
            }
        }
        return null;
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
