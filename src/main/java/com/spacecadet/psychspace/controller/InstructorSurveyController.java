package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/17/2017.
 */
@Controller
public class InstructorSurveyController {

    /**
     * instructor page - add new survey to course
     * @return
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.GET)
    public ModelAndView addSurvey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddSurvey");

        return model;
    }

    /**
     * instructor page - edit survey to course
     * @return
     */
    @RequestMapping(value = "/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurvey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditSurvey");

        return model;
    }

    /**
     * instructor page - delete survey to course
     * @return
     */
    @RequestMapping(value = "/deleteSurvey", method = RequestMethod.GET)
    public ModelAndView deleteSurvey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteSurvey");

        return model;
    }
}
