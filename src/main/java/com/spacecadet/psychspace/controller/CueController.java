package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Cue;
import com.spacecadet.psychspace.utilities.User;
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
public class CueController {

    private CueManager cueManager = new CueManager();
    private CourseManager courseManager = new CourseManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private GoalManager goalManager = new GoalManager();
    private HabitManager habitManager = new HabitManager();
    private SurveyManager surveyManager = new SurveyManager();
    private UserManager userManager = new UserManager();

    /**
     * user cues page
     * @param courseKey course key
     * @return cues page
     */
    @RequestMapping(value = "/learn/{courseKey}/cues", method = RequestMethod.GET)
    public ModelAndView loadCues(@PathVariable("courseKey") String courseKey){
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        String hasStarted = "false";
        ModelAndView model = new ModelAndView();
        model.setViewName("learnCues");
        model.addObject("cue", new Cue());
        model.addObject("negativeCues", cueManager.loadUserCues(WelcomeController.currUser.getUserKey(), courseKey, "negative"));
        model.addObject("positiveCues", cueManager.loadUserCues(WelcomeController.currUser.getUserKey(), courseKey, "positive"));
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
        if(surveyManager.loadUserSurvey(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasSurvey = "true";
        }
        if(courseManager.hasStarted(course.getStartDate())){
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
     * add user cue to cue list
     * @param courseKey course key in datastore
     * @param cue new cue created
     * @return redirect to course cues page
     */
    @RequestMapping(value = "/learn/{courseKey}/cues/addCue", method = RequestMethod.POST)
    public String addCues(@PathVariable("courseKey") String courseKey, @ModelAttribute("cue") Cue cue){
        cueManager.addCue(cue);
        return "redirect:/learn/"+courseKey+"/cues";
    }

    /**
     *  edit user cue in cue list
     * @param courseKey course key in datastore
     * @param cue edited cur
     * @return redirect to course cue page
     */
    @RequestMapping(value = "/learn/{courseKey}/cues/editCue", method = RequestMethod.POST)
    public String editCues(@PathVariable("courseKey") String courseKey, @ModelAttribute("cue") Cue cue){
        cueManager.editCue(cue);
        return "redirect:/learn/"+courseKey+"/cues";
    }

    /**
     * logout on cue pages
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/cues/logout", method = RequestMethod.POST)
    public String logoutCuePage() {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
