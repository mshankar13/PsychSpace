package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.HelperManager;
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
    private HelperManager helper = new HelperManager();

    /**
     * all visit to learn page
     * @return learn page
     */
    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public ModelAndView learn() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learn");
        model.addObject("myCurrCourses", helper.shortenCourseList(
                courseManager.getCurrCourses(WelcomeController.currUser.getUserKey())));
        model.addObject("myPastCourses", helper.shortenCourseList(
                courseManager.getPastCourses(WelcomeController.currUser.getUserKey())));
        model.addObject("currUser", WelcomeController.currUser);
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
}
