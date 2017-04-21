package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/19/17.
 */
@Controller
public class VideoController {

    private UserManager userManager = new UserManager();

    /**
     * all visit to video page
     * @return
     */
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public ModelAndView survey() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learnVideos");

        return model;
    }

    /**
     * logout on video page
     * @return
     */
    @RequestMapping(value = "/video/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
