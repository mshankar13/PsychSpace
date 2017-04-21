package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.News;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
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
        model.setViewName("adminAddArticle");
        model.addObject("news", new News());
        return model;
    }

    /**
     * admin page (post) - add new article to news feed
     * @param news new created article
     * @return news page
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news) {
        if(news != null) {
            newsManager.addNews(news.getTitle(), news.getAuthor(), news.getContent(), news.getLikesCount(), news.getDate());
        }

        return "redirect:/news";
    }

    /**
     * admin page (get) - delete article from news feed
     * @return deleteArticle page
     */
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.GET)
    public ModelAndView deleteNews() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminDeleteArticle");
        ArrayList<News> newsList = newsManager.loadNews();
        model.addObject("newsList", newsList);
        model.addObject("news", new News());

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
        model.setViewName("adminEditArticle");
        ArrayList<News> newsList = newsManager.loadNews();
        model.addObject("newsList", newsList);
        model.addObject("news", new News());

        return model;
    }

    /**
     * admin page (post) - edit news article
     * @param news news edited
     * @return editNews page
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.POST)
    public ModelAndView editNews(@ModelAttribute("news") News news) {
        if (news != null)
            newsManager.editNews(news.getNewsKey(), news.getTitle(), news.getAuthor(),
                    news.getContent(), news.getLikesCount(), news.getDate());
        ModelAndView model = new ModelAndView();
        model.setViewName("adminEditArticle");
        ArrayList<News> newsList = newsManager.loadNews();
        model.addObject("newsList", newsList);
        model.addObject("news", new News());

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
