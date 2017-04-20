package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by marleneshankar on 4/19/17.
 */
@Controller
public class SurveyController {

    /**
     * all visit to survey page
     * @return
     */
    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public ModelAndView survey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learnSurvey");

        return model;
    }
}
