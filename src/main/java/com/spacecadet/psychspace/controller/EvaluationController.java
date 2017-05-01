package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.GoalManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.Goal;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * user evaluation page
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "/learn/{courseKey}/evaluation", method = RequestMethod.GET)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey){
        Goal goal = goalManager.loadSingleGoal(courseKey, WelcomeController.currUser.getUserKey());
        Date today = new Date();
        ModelAndView model = new ModelAndView();
        model.setViewName("learnEvaluation");
        Evaluation evaluation = new Evaluation();
        evaluation.setDate(today.toString());
        model.addObject("goal", goal);


        return model;
    }

    /**
     * user submit evaluation
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "learn/{courseKey}/evaluation/submit", method = RequestMethod.POST)
    public ModelAndView submitEvaluation(@PathVariable("courseKey") String courseKey){
        ModelAndView model = new ModelAndView();
        model.setViewName("evaluation");
        // TODO: make sure front end shows no more evaluation for the day
        return model;
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
