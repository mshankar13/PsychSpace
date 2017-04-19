package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by aliao on 3/20/2017.
 */
@Controller
public class HomeController {

    private UserManager userManager = new UserManager();

    /**
     * all visit to home page
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        model.setViewName("home");

        return model;
    }

    /**
     * logout on home page
     * @return
     */
    @RequestMapping(value = "/home/logout", method = RequestMethod.GET)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
