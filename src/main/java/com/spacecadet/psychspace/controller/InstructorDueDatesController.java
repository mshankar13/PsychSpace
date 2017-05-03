package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.DueDatesManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.DueDates;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to instructor evaluation pages.
 * use cases: set evaluation dates from selected course
 * Created by aliao on 4/21/2017.
 */
@Controller
public class InstructorDueDatesController {

    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();
    private DueDatesManager dueDatesManager = new DueDatesManager();


    /**
     * instructor page (get) - evaluations
     *
     * @return instructor load evaluation page
     */
    @RequestMapping(value = "/instructor/{courseKey}/dueDates", method = RequestMethod.GET)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey) {
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEvaluation");
        model.addObject("courses", courses);
        model.addObject("course", new Course());
        DueDates dueDates = dueDatesManager.loadDueDatesForCourse(courseKey);
        if(dueDates == null){
            dueDates = new DueDates();
        }
        model.addObject("dueDates", dueDates);

        return model;
    }

    /**
     * logout on instructor evaluation page
     *
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/{courseKey}/evaluations/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
