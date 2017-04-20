package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by marleneshankar on 4/19/17.
 */
@Controller
public class VideoController {

    /**
     * all visit to video page
     * @return
     */
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public ModelAndView survey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learnVideo");

        return model;
    }
}
