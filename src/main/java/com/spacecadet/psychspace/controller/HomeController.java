package com.spacecadet.psychspace.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.requests.AuthenticateUserRequest;
import com.spacecadet.psychspace.responses.AjaxResponseBody;
import com.spacecadet.psychspace.responses.JsonViews;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


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

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String afterRegister(@RequestBody String user, HttpServletRequest request){
        System.out.print("get: " + user);
        return "welcome";
    }


}
