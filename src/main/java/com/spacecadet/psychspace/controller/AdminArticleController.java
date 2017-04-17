package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by aliao on 4/10/2017.
 */
@Controller
public class AdminArticleController {

    private NewsManager newsManager = new NewsManager();
    private HelperManager helper = new HelperManager();

    /**
     * admin page - add new article to news feed
     * @return
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.GET)
    public ModelAndView loadNewList() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddArticle");
        model.addObject("news", new News());
        return model;
    }

    /**
     * admin page - add new article to news feed
     * @return
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news) {
        if(news != null) {
            newsManager.addNews(news.getTitle(), news.getAuthor(), news.getContent(), news.getLikesCount(), news.getDate());
        }

        return "redirect:/news";
    }

    /**
     * admin page - delete article from news feed
     * @return
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
     * admin page - delete article from news feed
     * @return
     */
    //TODO: AJAX always receives error even when return success
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.POST)
    public @ResponseBody String deleteNews(@RequestBody String newsList) {
        String[] list = helper.stringToJsonNewsKeyList(newsList);

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
     * admin page - edit news article
     * @return
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.GET)
    public ModelAndView editNews() {

        ModelAndView model = new ModelAndView();
        model.setViewName("adminEditArticle");
        ArrayList<News> newsList = newsManager.loadNews();
        System.out.print("total: " + newsList);
        model.addObject("newsList", newsList);
        model.addObject("news", new News());

        return model;
    }

    /**
     * admin page - edit article from the news feed
     * @return
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.POST)
    public String editNews(@ModelAttribute("news") News news) {
        if (news != null)
            newsManager.editNews(news.getNewsKey(), news.getTitle(), news.getAuthor(),
                    news.getContent(), news.getLikesCount(), news.getDate());

        return "admin_editArticle";
    }


}
