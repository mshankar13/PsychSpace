package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/21/2017.
 */
@Controller
public class InstructorEvaluationController {

    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();


    /**
     * instructor page (get) - evaluations
     * @return instructor load evaluation page
     */
    @RequestMapping(value = "/instructor/{courseKey}/evaluations", method = RequestMethod.GET)
    public ModelAndView loadEvaluation() {
        ArrayList<Course> courses = courseManager.loadAllCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddEvaluation");
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on instructor add evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/{courseKey}/evaluations/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/{courseKey}/evaluations/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/{courseKey}/evaluations/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
