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

        return model;
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public ModelAndView applicationSubmit() {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");

        return model;
    }

}
