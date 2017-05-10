package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to instructor course related pages.
 * use cases: add/edit selected course
 * Created by aliao on 4/15/17.
 */
@Controller
public class InstructorCourseController {

    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();

    /**
     * instructor page (post)- add new course to catalog post
     * @param course added course
     * @return instructor add course page
     */
    @RequestMapping(value = "/instructor/addCourse", method = RequestMethod.POST)
    public String addCoursePost(@ModelAttribute("course") Course course) {
        if(course != null) {
            course.setInstructor(WelcomeController.currUser.getFirstName() + " "
                    + WelcomeController.currUser.getLastName());
            course.setUserKey(WelcomeController.currUser.getUserKey());
            courseManager.addCourse(course);
        }

        return "redirect:/instructor/"+course.getCourseKey();
    }

    /**
     * instructor page (get) - edit course on catalog
     * @return instructor edit course page
     */
    @RequestMapping(value = "/instructor/{courseKey}", method = RequestMethod.GET)
    public ModelAndView editCourse(@PathVariable("courseKey") String courseKey) {
        ModelAndView model = new ModelAndView();
        if(!userManager.hasInstructorAccess()){
            model.setViewName("404");
            return model;
        }
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        model.setViewName("instructorCourse");
        model.addObject("course", new Course());
        model.addObject("currentCourse", courseManager.loadSingleCourse(courseKey));
        model.addObject("courses", courses);
        model.addObject("currUser", WelcomeController.currUser);
        return model;
    }

    /**
     * instructor page (post) - edit course on catalogue
     * @param course edited course
     * @return instructor edit course page
     */
    @RequestMapping(value = "/instructor/{courseKey}", method = RequestMethod.POST)
    public ModelAndView editCourse(@PathVariable("courseKey") String courseKey, @ModelAttribute("course") Course course) {
        if(course != null) {
            course.setCourseKey(courseKey);
            courseManager.editCourse(course);
        }
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorCourse");
        model.addObject("currentCourse", courseManager.loadSingleCourse(courseKey));
        model.addObject("course", new Course());
        model.addObject("courses",courses);

        return model;
    }

    /**
     * logout on instructor edit course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

}
