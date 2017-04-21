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

/**
 * Created by aliao on 4/15/17.
 */
@Controller
public class InstructorCourseController {

    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();

    /**
     * instructor page (get) - add new course to catalog
     * @return instructor add course page
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddCourse");
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page (post)- add new course to catalog post
     * @param course added course
     * @return instructor add course page
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
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
     * instructor page (get) - edit course on catalog
     * @return instructor edit course page
     */
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public ModelAndView editCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditCourse");
        model.addObject("course", new Course());
        model.addObject("courses", courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * instructor page (post) - edit course on catalog
     * @param course edited course
     * @return instructor edit course page
     */
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public ModelAndView editCourse(@ModelAttribute("course") Course course) {
        if(course != null)
            courseManager.editCourse(course);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditCourse");
        model.addObject("course", new Course());
        model.addObject("courses",courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * instructor page (get) - add new course to catalog
     * @return instructor delete course page
     */
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
    public ModelAndView deleteCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteCourse");
        model.addObject("course", new Course());
        model.addObject("courses",courseManager.loadInstructorCourses(WelcomeController.currUser.getUserKey()));

        return model;
    }

    /**
     * logout on instructor add course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/addCourse/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/editCourse/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/deleteCourse/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
