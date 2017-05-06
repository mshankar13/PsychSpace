package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.CueManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Cue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 5/1/2017.
 */
@Controller
public class CueController {

    private CueManager cueManager = new CueManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * user cues page
     * @param courseKey course key
     * @return cues page
     */
    @RequestMapping(value = "/learn/{courseKey}/cues", method = RequestMethod.GET)
    public ModelAndView loadCues(@PathVariable("courseKey") String courseKey){
        ModelAndView model = new ModelAndView();
        model.setViewName("learnCues");
        model.addObject("cue", new Cue());
        model.addObject("cueList", cueManager.loadUserCues(WelcomeController.currUser.getUserKey(), courseKey));
        Course course = courseManager.loadSingleCourse(courseKey);
        model.addObject("courseTitle", course.getTitle());
        model.addObject("courseStartDate", course.getStartDate());

        return model;
    }
}
