package com.spacecadet.psychspace.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.requests.AuthenticateUserRequest;
import com.spacecadet.psychspace.responses.AjaxResponseBody;
import com.spacecadet.psychspace.responses.JsonViews;
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

    @JsonView(JsonViews.Public.class)
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public AjaxResponseBody afterRegister(@RequestBody AuthenticateUserRequest user){
        System.out.print("email: " + user.getEmail());
        AjaxResponseBody result = new AjaxResponseBody();

        if(user != null){
            result.setCode("200");
            result.setMsg("User email: " + user.getEmail());
        }

        return result;
    }


}
