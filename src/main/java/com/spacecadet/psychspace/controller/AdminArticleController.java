package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.Article;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Controller for all visits to admin article related pages
 * use case: add/edit/delete article
 * Created by aliao on 4/10/2017.
 */
@Controller
public class AdminArticleController {

    private NewsManager newsManager = new NewsManager();
    private HelperManager helper = new HelperManager();
    private UserManager userManager = new UserManager();

    /**
     * admin page (get) - add new article to news feed
     * @return addArticle page
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.GET)
    public ModelAndView loadNewList() {
        ModelAndView model = new ModelAndView();
        if(!userManager.hasAdminAccess()){
            model.setViewName("403");
            return model;
        }
        model.setViewName("adminAddArticle");
        model.addObject("article", new Article());
        model.addObject("currUser", WelcomeController.currUser);
        return model;
    }

    /**
     * admin page (post) - add new article to article feed
     * @param article new created article
     * @return article page
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") Article article) {
        if(article != null) {
            newsManager.addNews(article.getTitle(), article.getAuthor(), article.getContent(),
                    article.getLikesCount(), article.getDate());
        }

        return "redirect:/article";
    }

    /**
     * admin page (get) - delete article from news feed
     * @return deleteArticle page
     */
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.GET)
    public ModelAndView deleteNews() {
        ModelAndView model = new ModelAndView();
        if(!userManager.hasAdminAccess()){
            model.setViewName("403");
            return model;
        }
        model.setViewName("adminDeleteArticle");
        ArrayList<Article> articleList = newsManager.loadNews();
        model.addObject("articleList", articleList);
        model.addObject("article", new Article());
        model.addObject("currUser", WelcomeController.currUser);
        return model;
    }

    /**
     * admin page (post) - delte article from news feed
     * @param news news deleted (in json)
     * @return success msg to ajax
     */
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.POST)
    public @ResponseBody String deleteNews(@RequestBody String news) {
        String[] list = helper.stringToJsonNewsKeyList(news);
        try {
            for (String newsKey : list) {
                newsManager.deleteNews(newsKey);
            }
        }
        catch (Exception ex){
            return "Error";
        }

        return "{\"msg\":\"success\"}";
    }


    /**
     * admin page (get) - edit news article
     * @return editNews page
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.GET)
    public ModelAndView editNews() {
        ModelAndView model = new ModelAndView();
        if(!userManager.hasAdminAccess()){
            model.setViewName("403");
            return model;
        }
        model.setViewName("adminEditArticle");
        ArrayList<Article> articleList = newsManager.loadNews();
        model.addObject("articleList", articleList);
        model.addObject("article", new Article());
        model.addObject("currUser", WelcomeController.currUser);
        return model;
    }

    /**
     * admin page (post) - edit article article
     * @param article article edited
     * @return editNews page
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.POST)
    public ModelAndView editNews(@ModelAttribute("news") Article article) {
        if (article != null)
            newsManager.editNews(article.getNewsKey(), article.getTitle(), article.getAuthor(),
                    article.getContent(), article.getLikesCount(), article.getDate());
        ModelAndView model = new ModelAndView();
        model.setViewName("adminEditArticle");
        ArrayList<Article> articleList = newsManager.loadNews();
        model.addObject("articleList", articleList);
        model.addObject("article", new Article());

        return model;
    }

    /**
     * logout on admin add article page
     * @param user logged out user
     * @return welcome page
     */
    @RequestMapping(value = "/admin_addArticle/logout", method = RequestMethod.POST)
    public String logoutAdd(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on admin edit article page
     * @param user logged out user
     * @return welcome page
     */
    @RequestMapping(value = "/admin_editArticle/logout", method = RequestMethod.POST)
    public String logoutEdit(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * logout on admin delete article page
     * @param user logged out user
     * @return welcome page
     */
    @RequestMapping(value = "/admin_deleteArticle/logout", method = RequestMethod.POST)
    public String logoutDelete(@RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
