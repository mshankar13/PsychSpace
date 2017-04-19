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
        for(Course course : courses){
            if(course.getDescription().length() >= 100){
                course.setDescription(course.getDescription().substring(0, 100));
            }
        }
        model.addObject("courseList", courses);

        return model;
    }

    /**
     * after register on catalogue page
     * @param user
     * @return
     */
    @RequestMapping(value = "/catalogue", method = RequestMethod.POST)
    public String afterRegister(@RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        return "redirect:/catalogue";
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
     * create dummy data for courses
     */
    private void course_test(){
        courseManager.addCourse();
    }
}
