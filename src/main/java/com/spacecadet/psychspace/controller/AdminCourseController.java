package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by acliao on 4/15/17.
 */
@Controller
public class AdminCourseController {

    /**
     * admin page - add new course to catalog
     * @return
     */
    @RequestMapping(value = "/admin_addCourse", method = RequestMethod.GET)
    public ModelAndView courseList() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddCourse");

        return model;
    }

    /**
     * admin page - add new course to catalog
     * @return
     */
    @RequestMapping(value = "/admin_addCourse", method = RequestMethod.POST)
    public String addNews() {


        return "adminAddCourse";
    }

}
