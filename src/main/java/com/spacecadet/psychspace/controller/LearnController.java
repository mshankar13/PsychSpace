package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to student/user learn pages.
 * use cases: load user currently enrolled courses.
 * Created by aliao on 4/6/2017.
 */
@Controller
public class LearnController {

    private UserManager  userManager = new UserManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * all visit to learn page
     * @return learn page
     */
    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public ModelAndView learn() {
        ArrayList<Course> courses = courseManager.loadUserCourses(WelcomeController.currUser.getUserKey());
        for(Course c : courses){
            if(c.getTitle().length() >= 50){
                c.setTitle(c.getTitle().substring(0, 50));
            }
            if(c.getDescription().length() >= 100){
                c.setDescription(c.getDescription().substring(0, 100));
            }
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("learn");
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on learn page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on learn single course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
