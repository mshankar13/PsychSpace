package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.SurveyManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/17/2017.
 */
@Controller
public class InstructorSurveyController {

    private SurveyManager surveyManager = new SurveyManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * instructor page - add new survey to course
     * @return
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.GET)
    public ModelAndView addSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page - add new survey to course
     * @return
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
    public ModelAndView addSurvey(@ModelAttribute("survey") Survey survey) {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        surveyManager.addSurvey(survey);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);


        return model;
    }


    /**
     * instructor page - edit survey to course
     * @return
     */
    @RequestMapping(value = "/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page - delete survey to course
     * @return
     */
    @RequestMapping(value = "/deleteSurvey", method = RequestMethod.GET)
    public ModelAndView deleteSurvey() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteSurvey");
        model.addObject("survey", new Survey());
        model.addObject("courses", courses);

        return model;
    }
}
