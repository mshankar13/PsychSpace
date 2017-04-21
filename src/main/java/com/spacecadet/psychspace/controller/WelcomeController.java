package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.spacecadet.psychspace.dataManager.HelperManager;

/**
 * Created by aliao on 3/20/2017.
 */
@Controller
public class WelcomeController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();
    public static User currUser = new User();
    public static User currInstructor = new User();

    /**
     * all visit to url "/" to welcome page
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        User user2 = userManager.emailRegistered(user1.getEmail());
        if ( user2 == null) {
            user2 = userManager.addUser(user1, "User");
        }
        userManager.resetCurrentUser(user2);
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user){
        userManager.resetCurrentUser(new User());
        return "home";
    }

    /**
     * fix url visit to /_ah/warmup
     * @return
     */
    @RequestMapping(value = "/_ah/warmup", method = RequestMethod.GET)
    public ModelAndView notUsed1(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");

        return model;
    }

    /**
     * fix url visit to favicon.ico
     * @return
     */
    @RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
    public ModelAndView notUsed2(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");

        return model;
    }
}
