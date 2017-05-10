package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.org.joda.time.Days;
import com.google.appengine.repackaged.org.joda.time.DurationFieldType;
import com.google.appengine.repackaged.org.joda.time.LocalDate;
import com.spacecadet.psychspace.utilities.Evaluation;

import java.text.SimpleDateFormat;
import java.util.*;

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
        ArrayList<String> dates = new ArrayList<String>();
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
            dates.add(evaluation.getDate());
        }
        Collections.sort(evaluations, new Comparator<Evaluation>() {
            @Override
            public int compare(Evaluation o1, Evaluation o2) {
                Date date1 = helper.stringToDate(o1.getDate());
                Date date2 = helper.stringToDate(o2.getDate());
                return date2.compareTo(date1);
            }
        });
        if(evaluations.size() > 0){
            Date today = new Date();
            String author = evaluations.get(0).getAuthor();
            Date max = helper.stringToDate(evaluations.get(0).getDate());
            Date min = helper.stringToDate(evaluations.get(evaluations.size()-1).getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
            List<LocalDate> dateList = getAllDates(min, max);
            for(LocalDate date : dateList){
                String formattedDate = date.getMonthOfYear() + "/" + date.getDayOfMonth() + "/" + date.getYear();
                if(!dates.contains(formattedDate) && !date.equals(sdf.format(today))){
                    Evaluation evaluation = new Evaluation();
                    evaluation.setAuthorKey(userKey);
                    evaluation.setAuthor(author);
                    evaluation.setContent("");
                    evaluation.setDate(formattedDate);
                    evaluation.setCourseKey(courseKey);
                    evaluation.setScore("0");
                    evaluation.setRawScore("0");
                    addEvaluation(evaluation);
                    evaluations.add(evaluation);
                }
            }
        }
        Collections.sort(evaluations, new Comparator<Evaluation>() {
            @Override
            public int compare(Evaluation o1, Evaluation o2) {
                Date date1 = helper.stringToDate(o1.getDate());
                Date date2 = helper.stringToDate(o2.getDate());
                return date2.compareTo(date1);
            }
        });
        return evaluations;
    }

    private static List<LocalDate>  getAllDates(Date min, Date max){
        LocalDate startDate = new LocalDate(min);
        LocalDate endDate = new LocalDate(max);
        int days = Math.abs(Days.daysBetween(startDate, endDate).getDays());
        List<LocalDate> dates = new ArrayList<>(days);  // Set initial capacity to `days`.
        for (int i=1; i < days; i++) {
            LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        return dates;
    }

    private static LocalDate toJoda(LocalDate input) {
        return new LocalDate(input.getYear(),
                input.getMonthOfYear(),
                input.getDayOfMonth());
    }

    /**
     * chek if user submitted evalutaion for the day
     * @param userKey user key in datastore
     * @return true/false
     */
    public boolean hasTodaysEvaluation(String userKey, String courseKey){
        Date rawDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        String today = month + "/" + day + "/" + year;
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("AuthorKey", Query.FilterOperator.EQUAL, userKey);
        Query.Filter propertyFilter2 =
                new Query.FilterPredicate("CourseKey", Query.FilterOperator.EQUAL, courseKey);
        Query.CompositeFilter userCourseFilter = Query.CompositeFilterOperator.and(propertyFilter1, propertyFilter2);
        Query evaluationQuery = new Query("Evaluation").setFilter(userCourseFilter);
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
