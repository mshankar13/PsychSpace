package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.*;

import java.sql.Array;
import java.util.*;

/**
 * Created by marleneshankar on 4/14/17.
 * Modified by aliao on 04/19/17.
 */
public class CourseManager {
    private DatastoreService datastore;
    private HelperManager helper = new HelperManager();

    public CourseManager() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Loads all the courses
     *
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
        ArrayList<Course> result = new ArrayList<Course>();
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
     *
     * @param courseKey
     * @return
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
        } catch (EntityNotFoundException ex) {

        }
        return course;
    }

    /**
     * Loads all courses the instructor created
     *
     * @param userKey
     * @return Ordered list of courses by start date
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
     * @param userKey
     * @return
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
     *
     * @param courses
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
            loadedCourses.add(course);
        }

        // Sort the loaded News by date
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
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

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

            } catch (EntityNotFoundException ex) {

            }

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

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

    public ArrayList<Course> searchCourse(ArrayList<Course> courses, String keyword) {
        ArrayList<Course> titleSearch = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTitle().contains(keyword) == true) {
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
            if (course.getDescription().contains(keyword) == true) {
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
}
