package com.spacecadet.psychspace.controller;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.spacecadet.psychspace.dataManager.HelperManager;
import com.spacecadet.psychspace.dataManager.NewsManager;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Celeste on 4/14/17.
 */
@Controller
public class AdminDeleteArticleController {

    private NewsManager newsManager = new NewsManager();
    private HelperManager helper = new HelperManager();

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
    //TODO: AJAX always receives error even when return success
    @RequestMapping(value = "/admin_deleteArticle", method = RequestMethod.POST)
    public String deleteNews(@RequestBody String newsList) {
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

}
