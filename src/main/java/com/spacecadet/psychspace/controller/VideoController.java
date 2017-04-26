package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.dataManager.VideoManager;
import com.spacecadet.psychspace.utilities.User;
import com.spacecadet.psychspace.utilities.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/19/17.
 */
@Controller
public class VideoController {

    private UserManager userManager = new UserManager();
    private VideoManager videoManager = new VideoManager();

    /**
     * all visit to video page
     * @return learn video page
     */
    @RequestMapping(value = "/videos", method = RequestMethod.GET)
    public ModelAndView video() {
        ArrayList<Video> videos = videoManager.loadVideos();
        if(videos.size() > 0){
            for(Video v : videos){
                if(v.getTitle().length() >= 50){
                    v.setTitle(v.getTitle().substring(0, 50));
                }
            }
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("learnVideos");
        model.addObject("videos", videos);

        return model;
    }

    /**
     * logout on video page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/videos/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
