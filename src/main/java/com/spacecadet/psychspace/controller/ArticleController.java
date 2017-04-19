package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Like;
import com.spacecadet.psychspace.utilities.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 3/27/2017.
 */
@Controller
public class ArticleController {

    private CommentManager commentManager = new CommentManager();
    private NewsManager newsManager = new NewsManager();
    private LikeManager likeManager = new LikeManager();

    /**
     * all visits to article page
     * @return
     */
    @RequestMapping(value = "/article/{key}", method = RequestMethod.GET)
    public ModelAndView newsDetail(@PathVariable("key") String key) {
        ModelAndView model = new ModelAndView();
        model.setViewName("article");
        model.addObject("article", newsManager.loadSingleNews(key));
        model.addObject("like", new Like());
        News featured = newsManager.getFeatured(newsManager.loadNews());
        featured.setContent(featured.getContent().substring(0, 100));
        model.addObject("featured", featured);
        model.addObject("comment", new Comment());
        model.addObject("commentList", commentManager.loadComments(key));

        return model;
    }

    /**
     * add/edit/delete comment on an article
     * @param key
     * @param comment
     * @return
     */
    @RequestMapping(value = "/article/{key}", method = RequestMethod.POST)
    public ModelAndView addComment(@PathVariable("key") String key, @ModelAttribute Comment comment){
        if (comment.getState().equals("add")){
            commentManager.addComment(key,"currUser",comment.getContent());
        } else if(comment.getState().equals("edit")){
            commentManager.editComment(comment.getCommentKey(), "currUser", comment.getContent());
        } else if(comment.getState().equals("delete")){
            commentManager.deleteComment(comment.getCommentKey());
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("article");
        model.addObject("article", newsManager.loadSingleNews(key));
        model.addObject("like", new Like());
        News featured = newsManager.getFeatured(newsManager.loadNews());
        featured.setContent(featured.getContent().substring(0, 100));
        model.addObject("featured", featured);
        model.addObject("comment", new Comment());
        model.addObject("commentList", commentManager.loadComments(key));

        return model;
    }

    /**
     * like/unlike an article
     * @param key
     * @param like
     * @return
     */
    @RequestMapping(value = "/article/{key}/+1", method = RequestMethod.POST)
    public String likeArticle(@PathVariable("key") String key, @ModelAttribute Like like){
        News news = newsManager.loadSingleNews(key);
        ArrayList<Like> likeList = likeManager.loadLikes();
        // TODO: set like currUserID
        for(Like l : likeList){
            // if data already exists, its either liked or unliked
            if (like.getArticleID().equals(l.getArticleID()) && like.getUserID().equals(l.getUserID())){
                like.setLikeKey(l.getLikeKey());
                if(l.getStatus().equals("like")){  //if already like, unlike
                    like.setStatus("unlike");
                    likeManager.unlike(newsManager, news.getNewsKey(), news.getTitle(), news.getAuthor(),
                            news.getContent(), news.getLikesCount(), news.getDate(), like);
                    return "redirect:/article/"+key;
                } else if (l.getStatus().equals("unlike")){ // if already unlike, like
                    like.setStatus("like");
                    likeManager.like(newsManager, news.getNewsKey(), news.getTitle(), news.getAuthor(),
                            news.getContent(), news.getLikesCount(), news.getDate(), like);
                    return "redirect:/article/"+key;
                }
            }
        }
        // if data never exists, user is only trying to like
        like.setStatus("like");
        likeManager.like(newsManager, news.getNewsKey(), news.getTitle(), news.getAuthor(),
                news.getContent(), news.getLikesCount(), news.getDate(), like);
        return "redirect:/article/"+key;
    }


    /**
     * populate dummy comment data
     */
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
