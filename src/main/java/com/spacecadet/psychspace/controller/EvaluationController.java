package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.Goal;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Controller to all visits to user evaluation pages.
 * user cases: load evaluation, submit evaluation
 * Created by aliao on 4/26/2017.
 */
@Controller
public class EvaluationController {

    private UserManager userManager = new UserManager();
    private GoalManager goalManager = new GoalManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private SplitGoalManager splitGoalManager = new SplitGoalManager();
    private HabitManager habitManager = new HabitManager();
    private SurveyManager surveyManager = new SurveyManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * user evaluation page
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "/learn/{courseKey}/evaluation", method = RequestMethod.GET)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey){
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        String hasStarted = "false";
        Date rawDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        String today = month + "/" + day + "/" + year;
        Goal userGoal = goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("learnEvaluation");
        model.addObject("goal", userGoal);
        model.addObject("todayDate", today);
        double weeklyGoalValue = splitGoalManager.getSplitGoalValue(courseKey, userGoal);
        model.addObject("weeklyGoalValue", weeklyGoalValue);
        model.addObject("evaluation", new Evaluation());
        model.addObject("evaluationList", evaluationManager.loadUserEvaluations(courseKey, WelcomeController.currUser.getUserKey()));
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey(), courseKey)){
            hasEvaluation = "true";
        }
        if(habitManager.loadUserHabit(WelcomeController.currUser.getUserKey(), courseKey) != null){
            hasHabit = "true";
        }
        if(userGoal != null){
            hasGoal = "true";
        }
        if(surveyManager.loadUserSurvey(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasSurvey = "true";
        }
        if(courseManager.hasStarted(courseManager.loadSingleCourse(courseKey).getStartDate())){
            hasStarted = "true";
        }
        model.addObject("hasSurvey", hasSurvey);
        model.addObject("hasEvaluation", hasEvaluation);
        model.addObject("hasHabit", hasHabit);
        model.addObject("hasGoal", hasGoal);
        model.addObject("hasStarted", hasStarted);

        return model;
    }

    /**
     * user submit evaluation
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "learn/{courseKey}/evaluation/submit", method = RequestMethod.POST)
    public String submitEvaluation(@ModelAttribute("evaluation") Evaluation evaluation, @PathVariable("courseKey") String courseKey){
        Date rawDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        String today = month + "/" + day + "/" + year;
        evaluation.setAuthorKey(WelcomeController.currUser.getUserKey());
        evaluation.setAuthor(WelcomeController.currUser.getFirstName() + " " + WelcomeController.currUser.getLastName());
        evaluation.setDate(today);
        Goal userGoal = goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey());
        double weeklyGoalValue = splitGoalManager.getSplitGoalValue(courseKey, userGoal);
        double score = (Double.parseDouble(evaluation.getRawScore())/weeklyGoalValue) * 100.00;
        if(score >= 100) {
            score = 100;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        evaluation.setScore(Double.toString(Double.valueOf(df.format(score))));
        evaluationManager.addEvaluation(evaluation);
        return "redirect:/learn/"+courseKey+"/evaluation";
    }


    /**
     * logout on evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/evaluation/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
