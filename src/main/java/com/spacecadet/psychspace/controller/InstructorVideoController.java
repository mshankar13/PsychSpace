package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CourseManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.dataManager.VideoManager;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
     * instructor page (get) - load videos to course
     * @return instructor add video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos", method = RequestMethod.GET)
    public ModelAndView loadVideo(@PathVariable("courseKey") String courseKey) {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ArrayList<Video> videos = videoManager.loadVideoForCourse(courseKey);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorLoadVideo");
        model.addObject("courses", courses);
        model.addObject("videos", videos);

        return model;
    }

    /**
     * instructor page (get) - add new video to course
     * @return instructor add video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/add", method = RequestMethod.GET)
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
    @RequestMapping(value = "/instructor/{courseKey}/videos/add", method = RequestMethod.POST)
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
    @RequestMapping(value = "/instructor/{courseKey}/videos/edit", method = RequestMethod.GET)
    public ModelAndView editVideo() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("videos", videoManager.loadVideos());

        return model;
    }

    /**
     * instructor page - edit video to course
     * @return instructor edit video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/edit", method = RequestMethod.POST)
    public ModelAndView editVideo(@ModelAttribute("video") Video video) {
        videoManager.editVideo(video);
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorEditVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("videos", videoManager.loadVideos());

        return model;
    }

    /**
     * instructor page - delete video to course
     * @return instructor delete video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/delete", method = RequestMethod.POST)
    public ModelAndView deleteVideo(@ModelAttribute("video") Video video) {
        videoManager.deleteVideo(video.getVideoKey());
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("videos", videoManager.loadVideos());

        return model;
    }

    /**
     * instructor page - delete video to course
     * @return instructor delete video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/delete", method = RequestMethod.GET)
    public ModelAndView deleteVideo() {
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorDeleteVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("videos", videoManager.loadVideos());

        return model;
    }

    /**
     * logout on instructor load video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/logout", method = RequestMethod.POST)
    public String logoutLoad(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor add video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/add/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/edit/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/delete/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
