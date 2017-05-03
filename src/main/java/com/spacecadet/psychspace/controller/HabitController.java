package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.GoalManager;
import com.spacecadet.psychspace.utilities.Goal;
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
public class HabitController {
    private GoalManager goalManager = new GoalManager();

    /**
     * user habit page
     * @param courseKey course key
     * @return goal page
     */
    @RequestMapping(value = "/learn/{courseKey}/habit", method = RequestMethod.GET)
    public ModelAndView loadGoal(@PathVariable("courseKey") String courseKey){
        Goal goal = goalManager.loadSingleGoal(courseKey, WelcomeController.currUser.getUserKey());
        if(goal == null){
            goal = new Goal();
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("learnGoal");
        model.addObject("goal", goal);
        model.addObject("courseKey", courseKey);

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
        Goal goal1 = goalManager.loadSingleGoal(courseKey, currUser.getUserKey());
        goal1.setUserKey(currUser.getUserKey());
        goal1.setUsername(currUser.getFirstName() + " " + currUser.getLastName());
        goal1.setGoalName(goal1.getUnit() + " " + goal1.getValue() + " per day.");
        if(goal1 == null){
            goalManager.addGoal(goal);
        } else {
            goalManager.editGoal(goal);
        }

        return "redirect:/learn/"+courseKey+"/goal";
    }
}
