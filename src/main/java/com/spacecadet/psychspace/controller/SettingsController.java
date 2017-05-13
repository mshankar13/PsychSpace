package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for all visits to settings page.
 * Created by Celeste on 5/7/17.
 * modified by aliao 5/10/17.
 */
@Controller
public class SettingsController {

    private UserManager userManager = new UserManager();

    /**
     * all visits to settings page
     * @return settings page
     */
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView model = new ModelAndView();
        model.setViewName("setting");
        model.addObject("currUser", WelcomeController.currUser);
        model.addObject("user", new User());

        return model;
    }

    /**
     * delete account on settings page
     * @return settings page
     */
    @RequestMapping(value = "/settings/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount() {
        userManager.deleteUser(WelcomeController.currUser.getUserKey());

        return "redirect:/";
    }

    /**
     * application page for applying to be admin or instructor
     * @return settings page
     */
    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public ModelAndView application() {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * submit application for applying to be admin or instructor
     * @param user current user
     * @return settings page
     */
    @RequestMapping(value = "/application/submit", method = RequestMethod.POST)
    public String applicationInstructorSubmit(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName("application");
        User currUser = WelcomeController.currUser;
        if (user != null) {
           currUser.setRole(user.getRole());
           userManager.updateUser(currUser);
        }

        return "redirect:/application";
    }

    /**
     * logout on settings page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/settings/logout", method = RequestMethod.POST)
    public String logoutOnSetting(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on settings applicationa page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/application/logout", method = RequestMethod.POST)
    public String logoutOnApplication(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

}
