package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by Celeste on 4/15/17.
 */
@Controller
public class AdminEditArticleController {

    private NewsManager newsManager = new NewsManager();
    private HelperManager helper = new HelperManager();

    /**
     * admin page - edit news article
     * @return
     */
    @RequestMapping(value = "/admin_editArticle", method = RequestMethod.GET)
    public ModelAndView newList() {

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
    public String deleteNews(@ModelAttribute("news") News news) {
        if (news != null)
            newsManager.editNews(news.getNewsKey(), news.getTitle(), news.getAuthor(),
                    news.getContent(), news.getLikesCount(), news.getDate());

        return "news";
    }

}
