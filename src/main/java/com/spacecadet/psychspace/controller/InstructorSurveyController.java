package com.spacecadet.psychspace.controller;

import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;
import com.google.appengine.repackaged.com.google.gson.JsonPrimitive;
import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.SurveyManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aliao on 4/17/2017.
 */
@Controller
public class InstructorSurveyController {

    private SurveyManager surveyManager = new SurveyManager();
    private CourseManager courseManager = new CourseManager();
    private HelperManager helperManager = new HelperManager();
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
        Survey survey1 = helperManager.surveyStringToJson(survey);
        survey1.setUserKey(WelcomeController.currUser.getUserKey());
        surveyManager.addSurvey(survey1);

        return "redirect:/addSurvey";
    }


    /**
     * instructor page  - edit survey to course
     * @return
     */
    @RequestMapping(value = "/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        HashMap<Survey, HashMap<Question, ArrayList<Answer>>> surveys = surveyManager.loadSurveys(WelcomeController.currUser.getUserKey());
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
