package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Like;
import com.spacecadet.psychspace.utilities.News;
import com.spacecadet.psychspace.utilities.User;
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
    private UserManager userManager = new UserManager();
    private HelperManager helper = new HelperManager();

    /**
     * all visits to article page
     * @param key article key
     * @return single article page
     */
    @RequestMapping(value = "/article/{key}", method = RequestMethod.GET)
    public ModelAndView newsDetail(@PathVariable("key") String key) {
        News news = newsManager.loadSingleNews(key);
        ModelAndView model = new ModelAndView();
        model.setViewName("article");
        model.addObject("article", news);
        model.addObject("like", new Like());
        News featured = newsManager.getFeatured(newsManager.loadNews());
        featured.setContent(featured.getContent().substring(0, 100));
        model.addObject("featured", featured);
        model.addObject("comment", new Comment());
        model.addObject("commentList", commentManager.loadComments(key));
        if(WelcomeController.currUser.getUserKey() != null){
            if(likeManager.isLiked(key, WelcomeController.currUser.getUserKey())){
                model.addObject("isLiked", "true");
            }
            model.addObject("isLoggedIn", "true");
        } else {
            model.addObject("isLiked", "false");
            model.addObject("isLoggedIn", "false");
        }
        model.addObject("currentUserKey", WelcomeController.currUser.getUserKey());
        //model.addObject("articleAuthor", userManager.loadSingleUser(news.getAuthor()));

        return model;
    }

    /**
     * add/edit/delete comment on an article
     * @param key article key
     * @param comment add/edit/delete comment
     * @return single article page
     */
    @RequestMapping(value = "/article/{key}", method = RequestMethod.POST)
    public String addComment(@PathVariable("key") String key, @ModelAttribute Comment comment){
        News news = newsManager.loadSingleNews(key);
        if (comment.getState().equals("add")){
            commentManager.addComment(key, WelcomeController.currUser.getUserKey(),comment.getContent());
        } else if(comment.getState().equals("edit")){
            commentManager.editComment(comment.getCommentKey(), "currUser", comment.getContent());
        } else if(comment.getState().equals("delete")){
            commentManager.deleteComment(comment.getCommentKey());
        }

        return "redirect:/article/"+key;
    }

    /**
     * like/unlike an article
     * @param key article key
     * @param like like/unlike object
     * @return single article page
     */
    @RequestMapping(value = "/article/{key}/+1", method = RequestMethod.POST)
    public String likeArticle(@PathVariable("key") String key, @ModelAttribute Like like){
        News news = newsManager.loadSingleNews(key);
        ArrayList<Like> likeList = likeManager.loadLikes();
        like.setUserKey(WelcomeController.currUser.getUserKey());
        for(Like l : likeList){
            // if data already exists, its either liked or unliked
            if (l.getArticleKey().equals(key) && l.getUserKey().equals(like.getUserKey())){
                like.setLikeKey(l.getLikeKey());
                if(l.getStatus().equals("like")){  //if already like, unlike
                    like.setStatus("unlike");
                    likeManager.editlike(newsManager, news.getNewsKey(), news.getTitle(), news.getAuthor(),
                            news.getContent(), news.getLikesCount(), news.getDate(), like);
                    return "redirect:/article/"+key;
                } else if (l.getStatus().equals("unlike")){ // if already unlike, like
                    like.setStatus("like");
                    likeManager.editlike(newsManager, news.getNewsKey(), news.getTitle(), news.getAuthor(),
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
     * logout on article page
     * @param key article key
     * @param user user logged out
     * @return single article page
     */
    @RequestMapping(value = "/article/{key}/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("key") String key, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/article/"+key;
    }

    /**
     * login on article page
     * @param key article key
     * @param user logged in user
     * @return single article page
     */
    @RequestMapping(value = "/article/{key}/login", method = RequestMethod.POST)
    public String login(@PathVariable("key") String key, @RequestBody String user){
        User user1 = (User)(helper.stringToJson(user, "User"));
        user1 = userManager.emailRegistered(user1.getEmail());
        if (user1 == null) {
            user1 = userManager.addUser(user1, "User");
        }
        userManager.resetCurrentUser(user1);
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
