package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.NewsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class ArticleController {
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView newsDetail() {
        ModelAndView model = new ModelAndView();
        model.setViewName("newsdetail");

        return model;
    }
}
