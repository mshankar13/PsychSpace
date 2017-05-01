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

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Controller for all visits to instructor survey pages.
 * use cases: add/edit surveys from selected course
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
    @RequestMapping(value = "/instructor/{courseKey}/survey", method = RequestMethod.GET)
    public ModelAndView addSurveyPost(@PathVariable("courseKey") String courseKey) {
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        Survey survey = surveyManager.loadSingleCourseSurvey(courseKey);
        String courseSurvey;
        if (survey != null)
            courseSurvey = helperManager.surveyObjectsToJsonString(survey);
        else
            courseSurvey = null;
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courseSurvey", courseSurvey);
        model.addObject("courses", courses);
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page (post) - add new survey to course
     * @param survey new survey
     * @return instructor add survey
     */
    @RequestMapping(value = "/instructor/{courseKey}/addSurvey", method = RequestMethod.POST)
    public String addSurveyGet(@PathVariable("courseKey") String courseKey, @RequestBody String survey) {
        Survey survey1 = helperManager.surveyStringToSurvey(survey);
        survey1.setUserKey(WelcomeController.currUser.getUserKey());
        surveyManager.addSurvey(survey1);

        return "redirect:/instructor/{courseKey}/survey";
    }


    /**
     * instructor page  - edit survey to course
     * @return
     */
    @RequestMapping(value = "/instructor/{courseKey}/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurveyGet(@PathVariable("courseKey") String courseKey) {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        Survey rawSurvey = surveyManager.loadSingleCourseSurvey(courseKey);
        String survey = helperManager.surveyObjectsToJsonString(rawSurvey);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditSurvey");
        model.addObject("survey", survey);
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on instructor add survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/addSurvey/logout", method = RequestMethod.POST)
    public String logoutAdd(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/editSurvey/logout", method = RequestMethod.POST)
    public String logoutEdit(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
