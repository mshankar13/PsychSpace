package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/6/2017.
 */
@Controller
public class LearnController {
    /**
     * all visit to learn page
     * @return
     */
    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public ModelAndView newsDetail() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learn");

        return model;
    }
}
