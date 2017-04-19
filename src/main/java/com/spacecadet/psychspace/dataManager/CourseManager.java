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
     * @param userKey
     * @return Ordered list of courses by start date
     */
    public ArrayList<Course> loadUserCourses(String userKey) {
        Query userListQuery = new Query("List", KeyFactory.stringToKey(userKey));
        List<Entity> userCourses =
                datastore.prepare(userListQuery).asList(FetchOptions.Builder.withDefaults());
        return entityToCourse(userCourses);
    }

    /**
     * Helper function to convert the Course Entity to Object
     * @param courses
     * @return Organized list of courses by start date
     */
    public ArrayList<Course> entityToCourse(List<Entity> courses) {
        ArrayList<Course> loadedCourses = new ArrayList<Course>();
        for (Entity entity: courses) {
            Course course = new Course();
            Key courseKey = entity.getKey();

            course.setCourseKey(KeyFactory.keyToString(courseKey));
            course.setName(entity.getProperty("Name").toString());
            course.setStartDate(entity.getProperty("StartDate").toString());
            course.setEndDate(entity.getProperty("EndDate").toString());
            course.setStatus(entity.getProperty("Status").toString());

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

    public void addCourse () {

    }

    public void editCourse () {

    }

    public void deleteCourse () {

    }

    public ArrayList<Course> searchCourse(ArrayList<Course> courses, String keyword) {
        ArrayList<Course> titleSearch = new ArrayList<>();
        for (Course course: courses) {
            if (course.getName().contains(keyword) == true) {   titleSearch.add(course);    }
        }
        Collections.sort(titleSearch, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        ArrayList<Course> descriptionSearch = new ArrayList<>();
        for (Course course: courses) {
            if (course.getDescription().contains(keyword) == true) {
                if (!titleSearch.contains(course)) {    descriptionSearch.add(course);  }
                }
        }
        Collections.sort(descriptionSearch, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        ArrayList<Course> results = new ArrayList<>();
        results.addAll(titleSearch);
        results.addAll(descriptionSearch);
        return results;
    }
}
