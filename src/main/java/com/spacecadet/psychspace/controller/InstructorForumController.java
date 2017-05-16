package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Thread;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aliao on 5/16/2017.
 */
@Controller
public class InstructorForumController {
    private ThreadManager threadManager = new ThreadManager();
    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();

    /**
     * all visits to course forum page
     * @param courseKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey){
        ArrayList<Thread> threads = threadManager.loadCourseThreads(courseKey);
        Course course = courseManager.loadSingleCourse(courseKey);
        ModelAndView model = new ModelAndView();
        model.setViewName("learnForum");
        if(!userManager.hasInstructorAccess()){
            model.setViewName("403");
            return model;
        }
        model.addObject("currUserKey", WelcomeController.currUser.getUserKey());
        model.addObject("thread", new Thread());
        model.addObject("threads", threads);
        model.addObject("course", course);
        model.addObject("currUser", WelcomeController.currUser);
        model.addObject("courseKey", courseKey);

        return model;
    }

    /**
     * add thread on forum page
     * @param courseKey course key in datastore
     * @param thread new thread created
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/addThread", method = RequestMethod.POST)
    public String addThread(@PathVariable("courseKey") String courseKey, @ModelAttribute Thread thread){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        thread.setCourseKey(courseKey);
        thread.setUserKey(WelcomeController.currUser.getUserKey());
        thread.setDate(df.format(today).toString());
        thread.setInThreadName("Instructor");
        threadManager.addThread(thread);
        return "redirect:/instructor/"+courseKey+"/forum";
    }

    /**
     * logout on forum pages
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/logout", method = RequestMethod.POST)
    public String logoutForumPage() {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
