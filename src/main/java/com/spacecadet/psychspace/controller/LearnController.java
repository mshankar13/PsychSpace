package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 4/6/2017.
 */
@Controller
public class LearnController {

    private UserManager  userManager = new UserManager();

    /**
     * all visit to learn page
     * @return
     */
    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public ModelAndView newsDetail() {
        ModelAndView model = new ModelAndView();
        model.setViewName("learn");

        return model;
    }

    /**
     * logout on learn page
     * @return
     */
    @RequestMapping(value = "/learn/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
