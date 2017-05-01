package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.SurveyManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Survey;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for all visits to student/user survey pages.
 * user cases: load survey and submit survey
 * Created by aliao on 4/19/17.
 */
@Controller
public class SurveyController {

    private UserManager userManager = new UserManager();
    private SurveyManager surveyManager = new SurveyManager();

    /**
     * all visit to survey page
     * @return learn survey page
     */
    @RequestMapping(value = "/learn/{courseKey}/survey", method = RequestMethod.GET)
    public ModelAndView survey(@PathVariable("courseKey") String courseKey) {
        Survey survey = surveyManager.loadSingleCourseSurvey(courseKey);
        ModelAndView model = new ModelAndView();
        model.setViewName("learnSurvey");
        model.addObject("survey", survey);

        return model;
    }

    /**
     * logout on survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/survey/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
