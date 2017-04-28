package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/26/2017.
 */
@Controller
public class InstructorController {

    private UserManager userManager = new UserManager();
    private CourseManager courseManager = new CourseManager();

    /**
     * all visit to instructor page
     * @return instructor page
     */
    @RequestMapping(value = "/instructor", method = RequestMethod.GET)
    public ModelAndView instructor(){
        ArrayList<Course> courses = courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey());
        ModelAndView model = new ModelAndView();
        model.setViewName("instructor");
        model.addObject("courses", courses);
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page (post)- add new course to catalog post
     * @param course added course
     * @return instructor add course page
     */
    @RequestMapping(value = "/instructor/addCourse", method = RequestMethod.POST)
    public ModelAndView addCourse(@ModelAttribute("course") Course course) {
        if(course != null) {
            course.setInstructor(WelcomeController.currUser.getUserKey());
            course.setUserKey(WelcomeController.currUser.getUserKey());
            courseManager.addCourse(course);
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddCourse");
        model.addObject("course", new Course());

        return model;
    }




    /**
     * logout on instructor page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}