package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.testData.CatalogTest;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to catalogue pages
 * use cases: load all courses, search for courses
 * Created by aliao on 3/27/2017.
 */
@Controller
public class CatalogController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();
    private CourseManager courseManager = new CourseManager();
    private CatalogTest test = new CatalogTest();

    /**
     * all visit to catalog page
     * @return catalog page
     */
    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public ModelAndView catalog() {
        ModelAndView model = new ModelAndView();
        model.setViewName("catalogue");
        ArrayList<Course> courses = courseManager.loadAllCourses();
        if(courses.isEmpty()){
            test.course_test();
            courses = courseManager.loadAllCourses();
        }
        ArrayList<Course> openCourses = courseManager.loadAllOpenCourses();
        courses = setCourseList(courses);
        openCourses = setCourseList(openCourses);
        model.addObject("courseList", courses);
        model.addObject("openCourses", openCourses);
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * search for course on catalog page
     * @param search search key word
     * @return search page with searc result
     */
    @RequestMapping(value = "/catalogue/search", method = RequestMethod.GET)
    public ModelAndView catalogSearch(@RequestParam(value = "search", required = false) String search){
        ModelAndView model = new ModelAndView();
        model.setViewName("catalogueSearch");
        model.addObject("courseList", courseManager.searchCourse(courseManager.loadAllCourses(), search));
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * helper method for setting course list into front end format
     * changes user id to username
     * @return ArrayList of course
     */
    private ArrayList<Course> setCourseList(ArrayList<Course> courses){
        for(Course course : courses){
            if(course.getDescription().length() >= 100){
                course.setDescription(course.getDescription().substring(0, 100));
            }
            User instructor = userManager.loadSingleUser(course.getUserKey());
            if(instructor != null){
                course.setInstructor(instructor.getFirstName() + " "
                        + instructor.getLastName());
            }
        }
        return courses;
    }

    /**
     * logout on catalog page
     * @param user user logged out
     * @return catalog page
     */
    @RequestMapping(value = "/catalogue/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/catalogue";
    }

    /**
     * login on catalog page
     * @param user logged in user
     * @return catalog page
     */
    @RequestMapping(value = "/catalogue/login", method = RequestMethod.POST)
    public String login(@RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        userManager.resetCurrentUser(user1);
        return "redirect:/catalogue";
    }

}
