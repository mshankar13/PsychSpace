package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Celeste on 5/7/17.
 */
@Controller
public class AdminUserController {

    @RequestMapping(value = "/admin_user", method = RequestMethod.GET)
    public ModelAndView adminUser() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminUser");
        return model;
    }
}
