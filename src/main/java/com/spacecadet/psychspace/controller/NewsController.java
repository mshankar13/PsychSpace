package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.testData.NewsTest;
import com.spacecadet.psychspace.utilities.Article;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to news page
 * Created by aliao on 3/20/2017.
 */
@Controller
public class NewsController {

    private NewsManager newsManager = new NewsManager();
    private UserManager userManager = new UserManager();
    private HelperManager helper = new HelperManager();
    private NewsTest test = new NewsTest();

    /**
     * all visit to news page
     * @return news page
     */
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView newList() {
        newsManager = new NewsManager();
        ModelAndView model = new ModelAndView();
        model.setViewName("news");
        ArrayList<Article> articleList = newsManager.loadNews();
        if(articleList.isEmpty()){
            test.news_test();
            articleList = newsManager.loadNews();
        }
        for(Article article : articleList){
            if(article.getTitle().length() >= 50){
                article.setTitle(article.getTitle().substring(0, 50));
            }
            if(article.getContent().length() >= 100) {
                article.setContent(article.getContent().substring(0, 100));
            }
        }
        model.addObject("articleList", articleList);
        Article featured = newsManager.getFeatured(articleList);
        featured.setContent(featured.getContent().substring(0, 100));
        model.addObject("featuredNews", featured);
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * search on news page for related articles
     * @param search search key word
     * @return search result page with related articles
     */
    @RequestMapping(value = "/news/search", method = RequestMethod.GET)
    public ModelAndView newsSearch(@RequestParam(value = "search", required = false) String search){
        ModelAndView model = new ModelAndView();
        model.setViewName("newsSearch");
        model.addObject("newsList", newsManager.searchNews(newsManager.loadNews(), search));
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * login on news page
     * @param user user logged on
     * @return news page
     */
    @RequestMapping(value = "/news/login", method = RequestMethod.POST)
    public String login(@RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        userManager.resetCurrentUser(user1);
        return "redirect:/news";
    }

    /**
     * logout on news page
     * @param user user logged on
     * @return news page
     */
    @RequestMapping(value = "/news/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String user){
        userManager.resetCurrentUser(new User());
        return "redirect:/news";
    }

    /**
     * helper method for shortening the content to show in frontend
     * @return list of news
     */
    private ArrayList<Article> shortenNews(){
        ArrayList<Article> articleList = newsManager.loadNews();
        if(articleList.isEmpty()){
            test.news_test();
            articleList = newsManager.loadNews();
        }
        for(Article article : articleList){
            if(article.getTitle().length() >= 50)
                article.setTitle(article.getTitle().substring(0, 50));
            if(article.getContent().length() >= 100)
                article.setContent(article.getContent().substring(0, 100));
        }
        return articleList;
    }

}
