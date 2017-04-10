package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.User;
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
public class ArticleController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();

    /**
     * all visit to article page
     * @return
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView newsDetail() {
        ModelAndView model = new ModelAndView();
        model.setViewName("article");

        return model;
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String afterRegister(@RequestBody String user, HttpServletRequest request){
        User user1 = (User)(helper.stringToJson(user, "User"));
        String key = userManager.emailRegistered(user1.email);
        if (key == null) {
            key = userManager.addUser(user1, "User");
        }
        return "article";
    }
}
