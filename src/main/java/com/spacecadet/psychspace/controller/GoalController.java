package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.GoalManager;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.Goal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by aliao on 5/1/2017.
 */
@Controller
public class GoalController {

    private GoalManager goalManager = new GoalManager();

    /**
     * user goal page
     * @param courseKey course key
     * @return goal page
     */
    @RequestMapping(value = "/learn/{courseKey}/goal", method = RequestMethod.GET)
    public ModelAndView loadGoal(@PathVariable("courseKey") String courseKey){
        Goal goal = goalManager.loadSingleGoal(courseKey, WelcomeController.currUser.getUserKey());
        if(goal == null){
            goal = new Goal();
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("learnGoal");
        model.addObject("goal", goal);

        return model;
    }

    /**
     * submit add/edit goal page
     * @param courseKey course key
     * @return goal page
     */
    @RequestMapping(value = "/learn/{courseKey}/goal/submit", method = RequestMethod.GET)
    public String submitGoal(@ModelAttribute("goal") Goal goal, @PathVariable("courseKey") String courseKey){
        Goal goal1 = goalManager.loadSingleGoal(courseKey, WelcomeController.currUser.getUserKey());
        if(goal1 == null){
            goalManager.addGoal(goal);
        } else {
            goalManager.editGoal(goal);
        }

        return "redirect:/learn/"+courseKey+"/goal";
    }
}
