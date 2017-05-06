package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.utilities.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for all visits to student/user progress pages.
 * use cases: load user progress for selected course
 * Created by aliao on 4/28/2017.
 */
@Controller
public class ProgressController {

    private CourseManager courseManager = new CourseManager();

    /**
     * all visit to course learn page
     * @return learn page
     */
    @RequestMapping(value = "/learn/{courseKey}", method = RequestMethod.GET)
    public ModelAndView learnCourse(@PathVariable("courseKey") String courseKey) {
        ModelAndView model = new ModelAndView();
        model.setViewName("learnProgress");
        Course course = courseManager.loadSingleCourse(courseKey);
        model.addObject("courseTitle", course.getTitle());
        model.addObject("courseStartDate", course.getStartDate());

        return model;
    }
}
