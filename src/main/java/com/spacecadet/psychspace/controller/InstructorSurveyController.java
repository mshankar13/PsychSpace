package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.SurveyManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Survey;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/17/2017.
 */
@Controller
public class InstructorSurveyController {

    private SurveyManager surveyManager = new SurveyManager();
    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();

    /**
     * instructor page (get) - add new survey to course
     * @return instructor add survey page
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.GET)
    public ModelAndView addSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page (post) - add new survey to course
     * @param survey new survey
     * @return instructor add survey
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
    public String addSurvey(@RequestBody String survey) {
        // TODO: add function for parsing

        return "redirect:/addSurvey";
    }


    /**
     * instructor page  - edit survey to course
     * @return
     */
    @RequestMapping(value = "/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page - delete survey to course
     * @return
     */
    @RequestMapping(value = "/deleteSurvey", method = RequestMethod.GET)
    public ModelAndView deleteSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on instructor add survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/addSurvey/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/editSurvey/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/deleteSurvey/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
