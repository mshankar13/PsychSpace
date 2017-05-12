package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Thread;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 5/6/2017.
 */
@Controller
public class ForumController {

    private ThreadManager threadManager = new ThreadManager();
    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private HabitManager habitManager = new HabitManager();
    private GoalManager goalManager = new GoalManager();
    private SurveyManager surveyManager = new SurveyManager();

    /**
     * all visits to course forum page
     * @param courseKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey){
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        String hasStarted = "false";
        ArrayList<Thread> threads = threadManager.loadCourseThreads(courseKey);
        Course course = courseManager.loadSingleCourse(courseKey);
        ModelAndView model = new ModelAndView();
        model.addObject("currUserKey", WelcomeController.currUser.getUserKey());
        model.addObject("thread", new Thread());
        model.addObject("threads", threads);
        model.setViewName("learnForum");
        model.addObject("course", course);
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey(), courseKey)){
            hasEvaluation = "true";
        }
        if(habitManager.loadUserHabit(WelcomeController.currUser.getUserKey(), courseKey) != null){
            hasHabit = "true";
        }
        if(goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey()) != null){
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
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * add thread on forum page
     * @param courseKey course key in datastore
     * @param thread new thread created
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/addThread", method = RequestMethod.POST)
    public String addThread(@PathVariable("courseKey") String courseKey, @ModelAttribute Thread thread){
        threadManager.addThread(thread);
        return "redirect:/learn/"+courseKey+"forum";
    }

    /**
     * logout on forum pages
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/logout", method = RequestMethod.POST)
    public String logoutForumPage() {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

}
