package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 3/20/2017.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");

        return model;
    }

    @RequestMapping(value = "/_ah/warmup", method = RequestMethod.GET)
    public ModelAndView notUsed(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");

        return model;
    }
}
