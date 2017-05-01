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
        model.setViewName("instructorVideo");
        model.addObject("courses", courses);
        model.addObject("videos", videos);
        model.addObject("video", new Video());
        model.addObject("courseKey", courseKey);
        model.addObject("course", new Course());

        return model;
    }

    /**
     * instructor page (post) - add new video to course
     * @return instructor add video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/add", method = RequestMethod.POST)
    public String addVideo(@PathVariable("courseKey") String courseKey, @ModelAttribute("video") Video video) {
        Course course = courseManager.loadSingleCourse(courseKey);
        video.setCourseTitle(course.getTitle());
        // make sure the video url is the embed one
        String url = video.getUrl().replace("watch?v=", "embed/");
        video.setUrl(url);
        videoManager.addVideo(video);
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("course", new Course());

        return "redirect:/instructor/{courseKey}/videos";
    }

    /**
     * instructor page - edit video to course
     * @return instructor edit video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/edit", method = RequestMethod.POST)
    public String editVideo(@PathVariable("courseKey") String courseKey, @ModelAttribute("video") Video video) {
        videoManager.editVideo(video);
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("course", new Course());
        model.addObject("videos", videoManager.loadVideos());

        return "redirect:/instructor/{courseKey}/videos";
    }

    /**
     * instructor page - delete video to course
     * @return instructor delete video page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/delete", method = RequestMethod.POST)
    public String deleteVideo(@PathVariable("courseKey") String courseKey, @ModelAttribute("video") Video video) {
        videoManager.deleteVideo(video.getVideoKey());
        ArrayList<Course> courses = courseManager.loadAllOpenCourses();
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorVideo");
        model.addObject("video", new Video());
        model.addObject("courses", courses);
        model.addObject("videos", videoManager.loadVideos());
        model.addObject("course", new Course());

        return "redirect:/instructor/{courseKey}/videos";
    }

    /**
     * logout on instructor load video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/logout", method = RequestMethod.POST)
    public String logoutLoad(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor add video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/add/logout", method = RequestMethod.POST)
    public String logoutAdd(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor edit video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/edit/logout", method = RequestMethod.POST)
    public String logoutEdit(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on instructor delete video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/videos/delete/logout", method = RequestMethod.POST)
    public String logoutDelete(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
