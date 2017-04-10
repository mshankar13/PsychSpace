package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
