package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 4/10/2017.
 */
@Controller
public class AdminAddArticleController {

    private NewsManager newsManager = new NewsManager();

    /**
     * admin page - add new article to news feed
     * @return
     */
    @RequestMapping(value = "/admin_addArticle", method = RequestMethod.GET)
    public ModelAndView newList() {
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
        System.out.print(news);
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
        System.out.print("total: " + newsList);
        model.addObject("newsList", newsList);
        model.addObject("news", new News());

        return model;
    }

    /**
     * admin page - delete article from news feed
     * @return
     */
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.POST)
    public String deleteNews(@ModelAttribute("news") News news) {
        System.out.print(news.getNewsKey());
        if(news != null) {
            newsManager.deleteNews(news.getNewsKey());
        }

        return "redirect:/news";
    }

//    TODO: Complete this
    /**
     * admin page - add new article to news feed
     * @return
     */
    @RequestMapping(value = "/admin_addCourse", method = RequestMethod.GET)
    public ModelAndView courseList() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminAddCourse");
        model.addObject("news", new News());
        return model;
    }

    // TODO: Complete this
    /**
     * admin page - add new article to news feed
     * @return
     */
    @RequestMapping(value = "/admin_addCourse", method = RequestMethod.POST)
    public String addNews() {


        return "redirect:/news";
    }
}
