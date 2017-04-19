package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aliao on 4/5/2017.
 */
@Controller
public class CourseController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();

    /**
     * all visit to course page
     * @return
     */
    @RequestMapping(value = "/course/{key}", method = RequestMethod.GET)
    public ModelAndView course(@PathVariable("key") String key) {
        ModelAndView model = new ModelAndView();
        model.setViewName("course");

        return model;
    }

    /**
     * after register
     * @param user
     * @return
     */
    @RequestMapping(value = "/course/{key}", method = RequestMethod.POST)
    public String afterRegister(@PathVariable("key") String key, @RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        return "redirect:/course/"+key;
    }

    /**
     * logout on course page
     * @return
     */
    @RequestMapping(value = "/course/{key}/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/course/{key}";
    }

    /**
     * login on course page
     * @param user
     * @return
     */
    @RequestMapping(value = "/course/{key}/login", method = RequestMethod.POST)
    public String login(@RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        userManager.resetCurrentUser(user1);
        return "redirect:/course/{key}";
    }
}
