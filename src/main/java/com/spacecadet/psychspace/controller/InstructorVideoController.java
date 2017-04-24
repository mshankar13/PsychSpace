package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.dataManager.VideoManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/21/2017.
 */
@Controller
public class InstructorVideoController {

    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();
    private VideoManager videoManager = new VideoManager();

    /**
     * instructor page (get) - add new video to course
     * @return instructor add video page
     */
    @RequestMapping(value = "/addVideo", method = RequestMethod.GET)
    public ModelAndView addVideo() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page (post) - add new video to course
     * @return instructor add video page
     */
    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)
    public ModelAndView addVideo(@ModelAttribute("video") Video video) {
        videoManager.addVideo(video);
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorAddVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }


    /**
     * instructor page - edit video to course
     * @return instructor edit video page
     */
    @RequestMapping(value = "/editVideo", method = RequestMethod.GET)
    public ModelAndView editVideo() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page - edit video to course
     * @return instructor edit video page
     */
    @RequestMapping(value = "/editVideo", method = RequestMethod.POST)
    public ModelAndView editVideo(@ModelAttribute("video") Video video) {
        videoManager.editVideo(video);
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * instructor page - delete video to course
     * @return instructor delete video page
     */
    @RequestMapping(value = "/deleteVideo", method = RequestMethod.GET)
    public ModelAndView deleteVideo() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);

        return model;
    }

    /**
     * logout on instructor add video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/addVideo/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/editVideo/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/deleteVideo/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
