package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class CatalogController {

    /**
     * all visit to catalog page
     * @return
     */
    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public ModelAndView catalog() {
        ModelAndView model = new ModelAndView();
        model.setViewName("catalogue");

        return model;
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.POST)
    public String afterRegister(@RequestBody String user, HttpServletRequest request){
        System.out.print("get: " + user);
        return "catalogue";
    }
}
