package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 5/1/2017.
 */
@Controller
public class HabitController {

    private CourseManager courseManager = new CourseManager();
    private GoalManager goalManager = new GoalManager();
    private HabitManager habitManager = new HabitManager();
    private CueManager cueManager = new CueManager();
    private DueDatesManager dueDatesManager = new DueDatesManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private SurveyManager surveyManager = new SurveyManager();

    /**
     * user habit page
     * @param courseKey course key
     * @return goal page
     */
    @RequestMapping(value = "/learn/{courseKey}/habit", method = RequestMethod.GET)
    public ModelAndView loadGoal(@PathVariable("courseKey") String courseKey){
        String hasGoal = "true";
        String hasHabit = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        ModelAndView model = new ModelAndView();
        DueDates dueDates = dueDatesManager.loadDueDatesForCourse(courseKey);
        Goal goal = goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey());
        if(goal == null){
            hasGoal = "false";
            goal = new Goal();
        }
        Habit habit = habitManager.loadUserHabit(WelcomeController.currUser.getUserKey(), courseKey);
        Cue cue;
        if(habit == null){
            cue = new Cue();

        } else {
            hasHabit = "true";
            cue = cueManager.loadSingleCue(habit.getCueKey());
        }
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey())){
            hasEvaluation = "true";
        }
        Course course = courseManager.loadSingleCourse(courseKey);
        model.addObject("courseTitle", course.getTitle());
        model.addObject("courseStartDate", course.getStartDate());
        model.setViewName("learnHabit");
        model.addObject("goal", goal);
        model.addObject("habit", habit);
        model.addObject("cue", cue);
        model.addObject("courseKey", courseKey);
        model.addObject("dueDates", dueDates);
        model.addObject("hasEvaluation", hasEvaluation);
        model.addObject("hasHabit", hasHabit);
        model.addObject("hasGoal", hasGoal);
        if(surveyManager.loadUserSurvey(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasSurvey = "true";
        }
        model.addObject("hasSurvey", hasSurvey);

        return model;
    }

    /**
     * submit add/edit goal on habit page
     * @param courseKey course key
     * @return goal page
     */
    @RequestMapping(value = "/learn/{courseKey}/habit/submitGoal", method = RequestMethod.POST)
    public String submitGoal(@ModelAttribute("goal") Goal goal, @PathVariable("courseKey") String courseKey){
        User currUser = WelcomeController.currUser;
        Goal myGoal = goalManager.loadUserGoal(courseKey, currUser.getUserKey());
        if(myGoal == null){
            goal.setUserKey(currUser.getUserKey());
            goal.setUserName(currUser.getFirstName() + " " + currUser.getLastName());
            goal.setCourseKey(courseKey);
            goalManager.addGoal(goal);
        } else {
            myGoal.setUserKey(currUser.getUserKey());
            myGoal.setUserName(currUser.getFirstName() + " " + currUser.getLastName());
            myGoal.setGoalName(goal.getGoalName());
            myGoal.setUnit(goal.getUnit());
            myGoal.setValue(goal.getValue());
            goalManager.editGoal(myGoal);
        }

        return "redirect:/learn/"+courseKey+"/habit";
    }
}
