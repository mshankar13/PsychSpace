package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/19/17.
 */
@Controller
public class SurveyController {

    private UserManager userManager = new UserManager();

    /**
     * all visit to survey page
     * @return learn survey page
     */
    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public ModelAndView survey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learnSurvey");

        return model;
    }

    /**
     * logout on survey page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/survey/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
