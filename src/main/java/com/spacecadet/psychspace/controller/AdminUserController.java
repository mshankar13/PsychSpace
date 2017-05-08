package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 5/8/2017.
 */
@Controller
public class AdminUserController {

    private UserManager userManager = new UserManager();

    /**
     * admin page (get) - change user to instructor
     * @return add instructor page
     */
    @RequestMapping(value = "/admin_addInstructor", method = RequestMethod.GET)
    public ModelAndView loadAddInstructor() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddInstructor");

        return model;
    }

    /**
     * admin page (post) - change user to instructor
     * @return add instructor page
     */
    @RequestMapping(value = "/admin_addInstructor", method = RequestMethod.POST)
    public ModelAndView addInstructor(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddInstructor");

        return model;
    }

    /**
     * admin page (get) - change user to admin
     * @return add instructor page
     */
    @RequestMapping(value = "/admin_addAdmin", method = RequestMethod.GET)
    public ModelAndView loadAddAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddAdmin");

        return model;
    }

    /**
     * admin page (post) - change user to admin
     * @return add instructor page
     */
    @RequestMapping(value = "/admin_addAdmin", method = RequestMethod.POST)
    public ModelAndView addAdmin(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddAdmin");

    }


    @RequestMapping(value = "/admin_user", method = RequestMethod.GET)
    public ModelAndView adminUser() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminUser");
        return model;
    }
}
