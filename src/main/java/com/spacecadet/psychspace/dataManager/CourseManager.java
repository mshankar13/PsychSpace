package com.spacecadet.psychspace.dataManager;

import com.google.appengine.api.datastore.*;
import com.spacecadet.psychspace.utilities.*;

import java.sql.Array;
import java.util.*;

/**
 * Created by marleneshankar on 4/14/17.
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
     * Loads all courses the user is enrolled in
     *
     * @param userKey
     * @return Ordered list of courses by start date
     */
    public ArrayList<Course> loadUserCourses(String userKey) {
        Query userListQuery = new Query("Course", KeyFactory.stringToKey(userKey));
        List<Entity> userCourses =
                datastore.prepare(userListQuery).asList(FetchOptions.Builder.withDefaults());
        return entityToCourse(userCourses);
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

    public void addCourse(String userKey, String title, String instructor, String description, String startDate,
                          String endDate, String enrollDate, String status, String currSize, String capacity) {
        Transaction txn = datastore.beginTransaction();
        try {
            Entity course = new Entity("Course");
            course.setProperty("UserKey", userKey);
            course.setProperty("Title", title);
            course.setProperty("Instructor", instructor);
            course.setProperty("Description", description);
            course.setProperty("StartDate", startDate);
            course.setProperty("EndDate", endDate);
            course.setProperty("EnrollDate", enrollDate);
            course.setProperty("Status", status);
            course.setProperty("CurrSize", currSize);
            course.setProperty("Capacity", capacity);
            datastore.put(txn, course);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public void editCourse(String courseKey, String title, String instructor, String description, String startDate,
                           String endDate, String enrollDate, String status, String currSize, String capacity) {
        Transaction txn = datastore.beginTransaction();

        try {
            try {
                Entity updatedCourse = datastore.get(KeyFactory.stringToKey(courseKey));
                updatedCourse.setProperty("UserKey", updatedCourse.getProperty("UserKey").toString());
                updatedCourse.setProperty("Title", title);
                updatedCourse.setProperty("Instructor", instructor);
                updatedCourse.setProperty("Description", description);
                updatedCourse.setProperty("StartDate", startDate);
                updatedCourse.setProperty("EndDate", endDate);
                updatedCourse.setProperty("EnrollDate", enrollDate);
                updatedCourse.setProperty("Status", status);
                updatedCourse.setProperty("CurrSize", currSize);
                updatedCourse.setProperty("Capacity", capacity);
                datastore.delete(KeyFactory.stringToKey(courseKey));
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
