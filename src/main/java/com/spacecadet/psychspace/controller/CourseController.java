package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/5/2017.
 */
@Controller
public class CourseController {
    /**
     * all visit to course page
     * @return
     */
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ModelAndView course() {
        ModelAndView model = new ModelAndView();
        model.setViewName("course");

        return model;
    }
}
