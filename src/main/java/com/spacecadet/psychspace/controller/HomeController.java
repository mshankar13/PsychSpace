package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


/**
 * Controller for all visits to student/user home pages.
 * user cases: load all enrolled courses and past courses.
 * Created by aliao on 3/20/2017.
 */
@Controller
public class HomeController {

    private UserManager userManager = new UserManager();
    private NewsManager newsManager = new NewsManager();
    private CourseManager courseManager = new CourseManager();
    private HelperManager helper = new HelperManager();

    /**
     * all visit to home page
     * @return home page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("featuredNews", newsManager.getFeatured(newsManager.loadNews()));
        model.addObject("myCurrCourses", helper.shortenCourseList(
                courseManager.getCurrCourses(WelcomeController.currUser.getUserKey())));
        model.addObject("popularCourses", helper.shortenCourseList(
                courseManager.getPopularCourses()));
        return model;
    }

    /**
     * logout on home page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/home/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }


}
