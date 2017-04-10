package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CommentManager;
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
    private CommentManager commentManager = new CommentManager();

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

    public void comments_test() {
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wkM", "Person1", "WOW!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wkM", "Person2", "No way!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wkM", "Person1", "Comment");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wsM", "Person3", "Maybe");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wsM", "Person4", "omg!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDg1wsM", "Person5", "Test!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwgM", "Person2", "Very helpful");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwgM", "Person1", "interesting!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwgM", "Person4", "where did you find this!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwoM", "Person7", "test comment!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwoM", "Person9", "this is another comment!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwoM", "Person3", "omg!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwoM", "Person5", "this is weird!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwkM", "Person3", "time to sleep!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwkM", "Person5", "this makes me hungry!");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwkM", "Person2", "content");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwkM", "Person4", "back to work");
        commentManager.addComment("ahVkZXZ-cHN5Y2hzcGFjZS0xNjA5MjFyEQsSBE5ld3MYgICAgIDgtwkM", "Person1", "recommended");
    }
}
