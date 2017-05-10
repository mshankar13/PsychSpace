package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.CommentManager;
import com.spacecadet.psychspace.dataManager.ThreadManager;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Thread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by aliao on 5/6/2017.
 */
@Controller
public class ForumController {

    private CommentManager commentManager = new CommentManager();
    private ThreadManager threadManager = new ThreadManager();

    /**
     * all visits to course forum page
     * @param courseKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey){
        ArrayList<Comment> comments = commentManager.loadComments(courseKey);
        ArrayList<Thread> threads = threadManager.loadCourseThreads(courseKey);
        ModelAndView model = new ModelAndView();
        model.addObject("thread", new Thread());
        model.addObject("threads", threads);
        model.setViewName("learnForum");
        model.addObject("comments", comments);
        model.addObject("comment", new Comment());

        return model;
    }

    /**
     * add thread on forum page
     * @param courseKey course key in datastore
     * @param thread new thread created
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/addThread", method = RequestMethod.POST)
    public String addThread(@PathVariable("courseKey") String courseKey, @ModelAttribute Thread thread){
        threadManager.addThread(thread);
        return "redirect:/learn/"+courseKey+"forum";
    }

    /**
     * edit thread on forum page
     * @param courseKey course key in datastore
     * @param thread edited thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/editThread", method = RequestMethod.POST)
    public String editThread(@PathVariable("courseKey") String courseKey, @ModelAttribute Thread thread){
        threadManager.editThread(thread);
        return "redirect:/learn/"+courseKey+"forum";
    }

    /**
     * delete thread on forum page
     * @param courseKey course key in datastore
     * @param thread deleted thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/deleteThread", method = RequestMethod.POST)
    public String deleteThread(@PathVariable("courseKey") String courseKey, @ModelAttribute Thread thread){
        threadManager.deleteThread(thread.getThreadKey());
        return "redirect:/learn/"+courseKey+"forum";
    }

    /**
     * add/edit/delete comment on a thread
     * @param courseKey courseKey
     * @param comment add/edit/delete comment
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable("courseKey") String courseKey, @ModelAttribute Comment comment){
        String fullname = WelcomeController.currUser.getFirstName().concat(" ").concat(WelcomeController.currUser.getLastName());
        if (comment.getState().equals("add")){
            commentManager.addComment(courseKey, fullname, WelcomeController.currUser.getUserKey(),comment.getContent());
        } else if(comment.getState().equals("edit")){
            commentManager.editComment(comment.getCommentKey(), fullname, WelcomeController.currUser.getUserKey(), comment.getContent());
        } else if(comment.getState().equals("delete")){
            commentManager.deleteComment(comment.getCommentKey());
        }
        return "redirect:/learn/"+courseKey+"forum";
    }
}
