package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/17/2017.
 */
@Controller
public class AdminSurveyController {

    /**
     * admin page - add new survey to course
     * @return
     */
    @RequestMapping(value = "/admin_addSurvey", method = RequestMethod.GET)
    public ModelAndView courseList() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddSurvey");

        return model;
    }
}
