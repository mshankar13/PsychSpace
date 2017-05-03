package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.DueDatesManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.DueDates;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
     * instructor page (get) - due dates
     *
     * @return instructor load dueDates page
     */
    @RequestMapping(value = "/instructor/{courseKey}/dueDates", method = RequestMethod.GET)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey) {
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDueDates");
        model.addObject("courses", courses);
        model.addObject("course", new Course());
        model.addObject("courseKey", courseKey);
        model.addObject("currentCourse", courseManager.loadSingleCourse(courseKey));
        DueDates dueDates = dueDatesManager.loadDueDatesForCourse(courseKey);
        if(dueDates == null){
            dueDates = new DueDates();
        }
        model.addObject("dueDates", dueDates);

        return model;
    }

    /**
     * instructor page (get) - due dates
     *
     * @return instructor load dueDates page
     */
    @RequestMapping(value = "/instructor/{courseKey}/dueDates", method = RequestMethod.POST)
    public ModelAndView loadEvaluation(@PathVariable("courseKey") String courseKey,
                                       @ModelAttribute("dueDates") DueDates dueDates)
    {
        boolean hasHabit = dueDatesManager.loadDueDatesForCourse(courseKey) != null ? true : false;
        if (hasHabit && dueDates != null) {
            dueDates.setCourseKey(courseKey);
            dueDatesManager.editDueDates(dueDates);
        }
        else if (!hasHabit && dueDates != null){
            dueDates.setCourseKey(courseKey);
            dueDatesManager.addDueDate(dueDates);
        }

        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDueDates");
        model.addObject("courses", courses);
        model.addObject("course", new Course());
        model.addObject("courseKey", courseKey);
        model.addObject("dueDates", dueDates);

        return model;
    }

    /**
     * logout on instructor evaluation page
     *
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/dueDates/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
