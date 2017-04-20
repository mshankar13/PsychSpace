package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by acliao on 4/15/17.
 */
@Controller
public class InstructorCourseController {

    private CourseManager courseManager = new CourseManager();

    /**
     * instructor page - add new course to catalog
     * @return
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddCourse");
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page - add new course to catalog post
     * @return
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public ModelAndView addCourse(@ModelAttribute("course") Course course) {
        if(course != null)
            courseManager.addCourse(course);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddCourse");
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page - edit course on catalog
     * @return
     */
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public ModelAndView editCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditCourse");
        model.addObject("course", new Course());
        model.addObject("courses", courseManager.loadUserCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * instructor page - edit course on catalog
     * @return
     */
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public ModelAndView editCourse(@ModelAttribute("course") Course course) {
        if(course != null)
            courseManager.editCourse(course);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditCourse");
        model.addObject("course", new Course());
        model.addObject("courses",courseManager.loadUserCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * instructor page - add new course to catalog
     * @return
     */
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
    public ModelAndView deleteCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteCourse");
        model.addObject("course", new Course());
        model.addObject("courses",courseManager.loadUserCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

}
