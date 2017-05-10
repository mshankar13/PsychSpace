package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 5/8/2017.
 */
@Controller
public class AdminUserController {

    private UserManager userManager = new UserManager();

    @RequestMapping(value = "/admin_user", method = RequestMethod.GET)
    public ModelAndView adminUser() {
        ModelAndView model = new ModelAndView();
        if(!userManager.hasAdminAccess()){
            model.setViewName("404");
            return model;
        }
        model.setViewName("adminUser");
        ArrayList<User> instructorApplicants = userManager.loadApplications("instructorApplicant");
        model.addObject("instructorApplicants", instructorApplicants);
        ArrayList<User> adminApplicants = userManager.loadApplications("adminApplicant");
        model.addObject("adminApplicants", adminApplicants);
        model.addObject("user", new User());
        model.addObject("currUser", WelcomeController.currUser);
        return model;
    }

    /**
     * admin page (get) - change user to instructor
     * @return add instructor page
     */
    @RequestMapping(value = "/admin_user/submit", method = RequestMethod.POST)
    public String loadAddInstructor(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminUser");

        if (user != null) {
            User applicant = userManager.loadSingleUser(user.getUserKey());
            applicant.setRole(user.getRole());
            userManager.updateUser(applicant);
        }

        return "redirect:/admin_user";
    }

    /**
     * logout on admin user
     * @return welcome page
     */
    @RequestMapping(value = "/admin_user/logout", method = RequestMethod.POST)
    public String logoutAdminUser() {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
