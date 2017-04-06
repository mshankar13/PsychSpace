package com.spacecadet.psychspace.controller;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.requests.AuthenticateUserRequest;
import com.spacecadet.psychspace.responses.AjaxResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public AjaxResponseBody afterRegister(@RequestBody AuthenticateUserRequest request){
        System.out.print("name: " + request.getEmail());
        AjaxResponseBody result = new AjaxResponseBody();

        if(request != null){
            result.setCode("200");
            result.setMsg("User email: " + request.getEmail());
        }

        return result;
    }

}
