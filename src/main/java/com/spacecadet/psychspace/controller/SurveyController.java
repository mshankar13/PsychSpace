package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Course;
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
 * Modified by acerini on 5/11/17
 */
@Controller
public class SurveyController {

    private UserManager userManager = new UserManager();
    private SurveyManager surveyManager = new SurveyManager();
    private CourseManager courseManager = new CourseManager();
    private HelperManager helperManager = new HelperManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private HabitManager habitManager = new HabitManager();
    private GoalManager goalManager = new GoalManager();

    /**
     * all visit to survey page
     * @return learn survey page
     */
    @RequestMapping(value = "/learn/{courseKey}/survey", method = RequestMethod.GET)
    public ModelAndView survey(@PathVariable("courseKey") String courseKey) {
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "true";
        String hasStarted = "false";
        Survey survey = surveyManager.loadUserSurvey(courseKey, WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("learnSurvey");
        Course course = courseManager.loadSingleCourse(courseKey);
        model.addObject("courseTitle", course.getTitle());
        model.addObject("courseStartDate", course.getStartDate());
        model.addObject("courseKey", courseKey);
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey(), courseKey)){
            hasEvaluation = "true";
        }
        if(habitManager.loadUserHabit(WelcomeController.currUser.getUserKey(), courseKey) != null){
            hasHabit = "true";
        }
        if(goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasGoal = "true";
        }
        if(survey == null){
            survey = surveyManager.loadSingleCourseSurvey(courseKey, course.getUserKey());
            hasSurvey = "false";
        }
        if(courseManager.hasStarted(course.getStartDate())){
            hasStarted = "true";
        }
        model.addObject("survey", survey);
        model.addObject("hasSurvey", hasSurvey);
        model.addObject("hasEvaluation", hasEvaluation);
        model.addObject("hasHabit", hasHabit);
        model.addObject("hasGoal", hasGoal);
        model.addObject("hasStarted", hasStarted);
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * add new survey to course
     * @param survey new survey
     * @return student submit survey
     */
    @RequestMapping(value = "/learn/{courseKey}/survey/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@PathVariable("courseKey") String courseKey, @RequestBody String survey) {
        Survey survey1 = helperManager.surveyStringToSurvey(survey);
        survey1.setUserKey(WelcomeController.currUser.getUserKey());
        surveyManager.addSurvey(survey1);

        return "redirect:/learn/"+courseKey;
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
