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
     * instructor page (get) - add new evaluation to course
     * @return instructor add evaluation page
     */
    @RequestMapping(value = "/addEvaluation", method = RequestMethod.GET)
    public ModelAndView addEvaluation() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddEvaluation");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page (get) - edit evaluation to course
     * @return instructor edit evaluation page
     */
    @RequestMapping(value = "/editEvaluation", method = RequestMethod.GET)
    public ModelAndView editEvaluation() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditEvaluation");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page (get) - delete evaluation to course
     * @return instructor delete evaluation page
     */
    @RequestMapping(value = "/deleteEvaluation", method = RequestMethod.GET)
    public ModelAndView deleteEvaluation() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteEvaluation");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on instructor add evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/addEvaluation/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/editEvaluation/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete evaluation page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/deleteEvaluation/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
