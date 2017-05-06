package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.utilities.Thread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 5/6/2017.
 */
@Controller
public class ForumController {

    /**
     * all visits to course forum page
     * @param courseKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey){
        ModelAndView model = new ModelAndView();
        model.addObject("thread", new Thread());
        model.setViewName("learnForum");

        return model;
    }
}
