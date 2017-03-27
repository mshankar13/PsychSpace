package com.spacecadet.psychspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class CatalogController {

    /**
     * all visit to catalog page
     * @return
     */
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView catalog() {
        ModelAndView model = new ModelAndView();
        model.setViewName("");

        return model;
    }
}
