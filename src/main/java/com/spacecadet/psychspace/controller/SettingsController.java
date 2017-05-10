package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Celeste on 5/7/17.
 */
@Controller
public class SettingsController {

    private UserManager userManager = new UserManager();

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView model = new ModelAndView();
        model.setViewName("setting");

        return model;
    }

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public ModelAndView application() {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");
        model.addObject("user", WelcomeController.currUser);
        return model;
    }

    @RequestMapping(value = "/application/submit", method = RequestMethod.POST)
    public String applicationInstructorSubmit(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");
        User currUser = WelcomeController.currUser;
        if (user != null) {
           currUser.setRole(user.getRole());
           userManager.updateUser(currUser);
        }

        return "redirect:/application";
    }

}
