package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class CatalogController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * all visit to catalog page
     * @return
     */
    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public ModelAndView catalog() {
        ModelAndView model = new ModelAndView();
        model.setViewName("catalogue");
        ArrayList<Course> courses = courseManager.loadAllCourses();
        if(courses.isEmpty()){
            course_test();
            courses = courseManager.loadAllCourses();
        }
        ArrayList<Course> openCourses = courseManager.loadAllOpenCourses();
        for(Course course : courses){
            if(course.getDescription().length() >= 100){
                course.setDescription(course.getDescription().substring(0, 100));
            }
        }
        for(Course course : openCourses){
            if(course.getDescription().length() >= 100){
                course.setDescription(course.getDescription().substring(0, 100));
            }
        }
        model.addObject("courseList", courses);
        model.addObject("openCourses", openCourses);

        return model;
    }

    /**
     * logout on catalog page
     * @return
     */
    @RequestMapping(value = "/catalogue/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/catalogue";
    }

    /**
     * login on catalog page
     * @param user
     * @return

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

    /**
     * Dummy data for courses
     */
    public void course_test() {
        // Before use add user keys to first field of each call!
        Course course1 = new Course("", "", "Exam Time Management", "Bob", "this is a course",
                "3/3/17", "4/3/17", "3/2/17", "4/3/17", "closed", "20", "25");
        Course course2 = new Course("","", "Homework Time Management", "Angela", "this is a course",
                "4/3/17", "5/3/17", "4/3/17", "4/2/17", "open", "25", "30");
        Course course3 = new Course("", "","Application Time Management", "Celeste", "this is a course",
                "4/16/17", "5/30/17", "4/16/17", "5/2/17", "closed", "25", "25");
        courseManager.addCourse(course1);
        courseManager.addCourse(course2);
        courseManager.addCourse(course3);
    }
}
