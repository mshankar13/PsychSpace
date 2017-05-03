package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.*;

import javax.servlet.ServletContextListener;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by marleneshankar on 4/14/17.
 * Modified by aliao on 04/19/17.
 */
public class CourseManager {

    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

    /**
     * constructor
     */
    public CourseManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Loads all the courses
     * @return Ordered list of courses by start date
     */
    public ArrayList<Course> loadAllCourses() {
        Query newsQuery = new Query("Course");
        List<Entity> allCourses =
                datastore.prepare(newsQuery).asList(FetchOptions.Builder.withDefaults());
        return entityToCourse(allCourses);
    }

    /**
     * Loads all the open courses
     * @return
     */
    public ArrayList<Course> loadAllOpenCourses(){
        ArrayList<Course> result = new ArrayList<>();
        ArrayList<Course> list = loadAllCourses();
        for(Course item : list){
            if(item.getStatus().equals("open")){
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Load a single course
     * @param courseKey course key
     * @return course object
     */
    public Course loadSingleCourse(String courseKey) {
       Course course = new Course();
        try {
            Entity singleCourse = datastore.get(KeyFactory.stringToKey(courseKey));
            course.setUserKey(singleCourse.getProperty("UserKey").toString());
            course.setCourseKey(courseKey);
            course.setTitle(singleCourse.getProperty("Title").toString());
            course.setInstructor(singleCourse.getProperty("Instructor").toString());
            course.setDescription(singleCourse.getProperty("Description").toString());
            course.setStartDate(singleCourse.getProperty("StartDate").toString());
            course.setEndDate(singleCourse.getProperty("EndDate").toString());
            course.setEnrollDate(singleCourse.getProperty("EnrollDate").toString());
            course.setDropDate(singleCourse.getProperty("DropDate").toString());
            course.setStatus(singleCourse.getProperty("Status").toString());
            course.setCurrSize(singleCourse.getProperty("CurrSize").toString());
            course.setCapacity(singleCourse.getProperty("Capacity").toString());

            String key = KeyFactory.keyToString(singleCourse.getKey());
            boolean status = checkCourseStatus(key);
            if (status) course.setStatus("open");
            else course.setStatus("closed");
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        }
        return course;
    }

    /**
     * Loads all courses the instructor created
     * @param userKey instructor key
     * @return Ordered list of courses teaches by instructor
     */
    public ArrayList<Course> loadInstructorCourses(String userKey) {
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query userListQuery = new Query("Course").setFilter(propertyFilter1);
        List<Entity> userCourses =
                datastore.prepare(userListQuery).asList(FetchOptions.Builder.withDefaults());
        return entityToCourse(userCourses);
    }


    /**
     * loads all courses the user is enrolled in
     * @param userKey user key
     * @return Ordered list of courses of user
     */
    public ArrayList<Course> loadUserCourses(String userKey) {
        Query.Filter propertyFilter1 =
                new Query.FilterPredicate("UserKey", Query.FilterOperator.EQUAL, userKey);
        Query enrollQuery = new Query("Enroll").setFilter(propertyFilter1);
        List<Entity> enrollList =
                datastore.prepare(enrollQuery).asList(FetchOptions.Builder.withDefaults());
        List<Entity> userCourseList = new ArrayList<>();
        for (Entity enroll: enrollList) {
            Query courseQuery = new Query("Course", KeyFactory.stringToKey(enroll.getProperty("CourseKey").toString()));
            userCourseList.add(datastore.prepare(courseQuery).asSingleEntity());
        }
        return entityToCourse(userCourseList);
    }

    /**
     * Helper function to convert the Course Entity to Object
     * @param courses list of course entities from datastore
     * @return Organized list of courses by start date
     */
    public ArrayList<Course> entityToCourse(List<Entity> courses) {
        ArrayList<Course> loadedCourses = new ArrayList<Course>();
        for (Entity entity : courses) {
            Course course = new Course();
            Key courseKey = entity.getKey();
            course.setCourseKey(KeyFactory.keyToString(courseKey));
            course.setUserKey(entity.getProperty("UserKey").toString());
            course.setTitle(entity.getProperty("Title").toString());
            course.setInstructor(entity.getProperty("Instructor").toString());
            course.setDescription(entity.getProperty("Description").toString());
            course.setStartDate(entity.getProperty("StartDate").toString());
            course.setEndDate(entity.getProperty("EndDate").toString());
            course.setEnrollDate(entity.getProperty("EnrollDate").toString());
            course.setDropDate(entity.getProperty("DropDate").toString());
            course.setStatus(entity.getProperty("Status").toString());
            course.setCurrSize(entity.getProperty("CurrSize").toString());
            course.setCapacity(entity.getProperty("Capacity").toString());

            String key = KeyFactory.keyToString(courseKey);
            boolean status = checkCourseStatus(key);
            if (status) course.setStatus("open");
            else course.setStatus("closed");

            loadedCourses.add(course);
        }
        // Sort the loaded Article by date
        Collections.sort(loadedCourses, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                Date date1 = helper.stringToDate(o1.getStartDate());
                Date date2 = helper.stringToDate(o2.getStartDate());
                return date1.compareTo(date2);
            }
        });
        return loadedCourses;
    }

    /**
     * add new course to datastore
     * @param initCourse new course
     */
    public void addCourse(Course initCourse) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity course = new Entity("Course");
            course.setProperty("UserKey", initCourse.getUserKey());
            course.setProperty("Title", initCourse.getTitle());
            course.setProperty("Instructor", initCourse.getInstructor());
            course.setProperty("Description", initCourse.getDescription());
            course.setProperty("StartDate", initCourse.getStartDate());
            course.setProperty("EndDate", initCourse.getEndDate());
            course.setProperty("EnrollDate", initCourse.getEnrollDate());
            course.setProperty("DropDate", initCourse.getDropDate());
            course.setProperty("Status", initCourse.getStatus());
            course.setProperty("CurrSize", initCourse.getCurrSize());
            course.setProperty("Capacity", initCourse.getCapacity());
            datastore.put(txn, course);
            txn.commit();

            String courseKey = KeyFactory.keyToString(course.getKey());
            checkCourseStatus(courseKey);
            initCourse.setCourseKey(courseKey);
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * edit course in datastore
     * @param course edited course
     */
    public void editCourse(Course course) {
        Transaction txn = datastore.beginTransaction();
        try {
            try {
                Entity updatedCourse = datastore.get(KeyFactory.stringToKey(course.getCourseKey()));
                updatedCourse.setProperty("UserKey", updatedCourse.getProperty("UserKey").toString());
                updatedCourse.setProperty("Title", updatedCourse.getProperty("Title").toString());
                updatedCourse.setProperty("Instructor", updatedCourse.getProperty("Instructor").toString());
                updatedCourse.setProperty("Description", course.getDescription());
                updatedCourse.setProperty("StartDate", course.getStartDate());
                updatedCourse.setProperty("EndDate", course.getEndDate());
                updatedCourse.setProperty("EnrollDate", course.getEnrollDate());
                updatedCourse.setProperty("DropDate", course.getDropDate());
                updatedCourse.setProperty("Status", course.getStatus());
                updatedCourse.setProperty("CurrSize", updatedCourse.getProperty("CurrSize").toString());
                updatedCourse.setProperty("Capacity", course.getCapacity());
                datastore.delete(KeyFactory.stringToKey(course.getCourseKey()));
                datastore.put(updatedCourse);
                txn.commit();

                String key = KeyFactory.keyToString(updatedCourse.getKey());
                checkCourseStatus(key);
            } catch (EntityNotFoundException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * delete course in datastore
     * @param courseKey deleted course
     */
    public void deleteCourse(String courseKey) {
        Transaction txn = datastore.beginTransaction();
        try {
            datastore.delete(KeyFactory.stringToKey(courseKey));
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    /**
     * search for courses with related keyword
     * @param courses list of all courses
     * @param keyword user input keyword
     * @return list of all related courses
     */
    public ArrayList<Course> searchCourse(ArrayList<Course> courses, String keyword) {
        ArrayList<Course> titleSearch = new ArrayList<>();
        for (Course course : courses) {
            if (Pattern.compile(Pattern.quote(keyword),
                    Pattern.CASE_INSENSITIVE).matcher(course.getTitle()).find()) {
                titleSearch.add(course);
            }
        }
        Collections.sort(titleSearch, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        ArrayList<Course> descriptionSearch = new ArrayList<>();
        for (Course course : courses) {
            if (Pattern.compile(Pattern.quote(keyword),
                    Pattern.CASE_INSENSITIVE).matcher(course.getDescription()).find()) {
                if (!titleSearch.contains(course)) {
                    descriptionSearch.add(course);
                }
            }
        }
        Collections.sort(descriptionSearch, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        ArrayList<Course> results = new ArrayList<>();
        results.addAll(titleSearch);
        results.addAll(descriptionSearch);
        return results;
    }

    /**
     * Checks the course status and updates if the enroll date has passed
     * @param courseKey
     * @return true/false status
     */
    public boolean checkCourseStatus (String courseKey) {
        try {
            Key key = KeyFactory.stringToKey(courseKey);
            Entity course = datastore.get(key);
            String enrollDate = course.getProperty("EnrollDate").toString();
            Date date = new Date();
            Date date1 = helper.stringToDate(enrollDate);
            if (date.after(date1) == true) {
                Transaction txn = datastore.beginTransaction();
                try {
                    course.setProperty("Status", "closed");
                    datastore.delete(key);
                    datastore.put(course);
                    txn.commit();
                } finally {
                    if (txn.isActive()) {
                        txn.rollback();
                    }
                }
                return false;
            } else {
                return true;
            }
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    /**
     * get the past courses of a given user
     * @param userKey user key
     * @return pastCourses of the user
     */
    public ArrayList<Course> getPastCourses(String userKey) {
        ArrayList<Course> pastCourses = new ArrayList<>();
        ArrayList<Course> courses = loadUserCourses(userKey);
        Date date = new Date();
        for (Course course : courses) {
            if (date.after(helper.stringToDate(course.getEndDate())) == true) {
                pastCourses.add(course);
            }
        }
        return pastCourses;
    }

    /**
     * gets 3 popular courses currently open
     * @return list of the popular courses
     */
    public ArrayList<Course> getPopularCourse() {
        ArrayList<Course> popularCourses = new ArrayList<>();
        ArrayList<Course> openCourses = loadAllCourses();
        HashMap<Integer, Double> scores = new HashMap<>();
        int i = 0;
        for (Course course : openCourses) { // Calculate all the scores per course and hash to course index
            double score = Double.parseDouble(course.getCurrSize()) / Double.parseDouble(course.getCapacity());
            scores.put(i++, score);
        }

        Comparator<Map.Entry<Integer, Double>> scoreComparator = new Comparator<Map.Entry<Integer,Double>>() {

            @Override
            public int compare(Map.Entry<Integer, Double> e1, Map.Entry<Integer, Double> e2) {
                Double v1 = e1.getValue();
                Double v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };

        Set<Map.Entry<Integer, Double>> mapEntries = scores.entrySet();
        List<Map.Entry<Integer, Double>> entries = new ArrayList<>(mapEntries);
        Collections.sort(entries, scoreComparator);

        Collections.reverse(entries);
        if (entries.size() >= 3) {
            for (int j = 0; j < 3; j++) {
                popularCourses.add(openCourses.get(entries.get(j).getKey()));
            }
        } else {
            for(Map.Entry<Integer, Double> entry : entries){
                popularCourses.add(openCourses.get(entry.getKey()));
            }
        }

        return popularCourses;
    }

}
