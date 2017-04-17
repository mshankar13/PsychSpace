package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.News;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class ArticleController {

    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();
    private ArticleManager articleManager = new ArticleManager();
    private CommentManager commentManager = new CommentManager();
    private NewsManager newsManager = new NewsManager();

    /**
     * all visit to article page
     * @return
     */
    @RequestMapping(value = "/article/{key}", method = RequestMethod.GET)
    public ModelAndView newsDetail(@PathVariable("key") String key) {
        ModelAndView model = new ModelAndView();
        model.setViewName("article");
        model.addObject("article", newsManager.loadSingleNews(key));
        News featured = newsManager.getFeatured(newsManager.loadNews());
        featured.setContent(featured.getContent().substring(0, 100));
        model.addObject("featured", featured);
        model.addObject("comment", new Comment());
        model.addObject("commentList", commentManager.loadComments(key));

        return model;
    }

    @RequestMapping(value = "/article/{key}", method = RequestMethod.POST)
    public String afterRegister(@PathVariable("key") String key, @ModelAttribute Comment comment){
        //commentManager.addComment(key, comment.getContent());
        return "article/"+key;
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
