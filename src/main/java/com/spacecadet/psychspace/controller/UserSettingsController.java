package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Celeste on 5/7/17.
 */
@Controller
public class UserSettingsController {

    private UserManager userManager = new UserManager();

    @RequestMapping(value = "/{userKey}/settings", method = RequestMethod.GET)
    public ModelAndView settings(@PathVariable("userKey") String userKey) {
        ModelAndView model = new ModelAndView();
        model.setViewName("setting");
        model.addObject("user", WelcomeController.currUser);


        return model;
    }

    @RequestMapping(value = "/{userKey}/application", method = RequestMethod.GET)
    public ModelAndView application(@PathVariable("userKey") String userKey) {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");
        model.addObject("user", WelcomeController.currUser);


        return model;
    }

}
