package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.EvaluationManager;
import com.spacecadet.psychspace.dataManager.GoalManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Evaluation;
import com.spacecadet.psychspace.utilities.Goal;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private EvaluationManager evaluationManager = new EvaluationManager();

    /**
     * user evaluation page
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "/learn/{courseKey}/evaluation", method = RequestMethod.GET)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey){
        Goal goal = goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("learnEvaluation");
        model.addObject("goal", goal);
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey())){
            model.addObject("hasEvaluation", "true");
        } else {
            model.addObject("hasEvaluation", "false");
        }
        model.addObject("evaluation", new Evaluation());
        model.addObject("evaluationList", evaluationManager.loadUserEvaluations(courseKey, WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * user submit evaluation
     * @param courseKey course key
     * @return evaluation page
     */
    @RequestMapping(value = "learn/{courseKey}/evaluation/submit", method = RequestMethod.POST)
    public String submitEvaluation(@ModelAttribute("evaluation") Evaluation evaluation, @PathVariable("courseKey") String courseKey){
        Date today = new Date();
        evaluation.setAuthorKey(WelcomeController.currUser.getUserKey());
        evaluation.setAuthor(WelcomeController.currUser.getFirstName() + " " + WelcomeController.currUser.getLastName());
        evaluation.setDate(today.toString());
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
