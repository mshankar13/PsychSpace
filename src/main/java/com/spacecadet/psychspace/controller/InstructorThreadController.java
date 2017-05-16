package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Thread;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aliao on 5/16/2017.
 */
@Controller
public class InstructorThreadController {
    private CommentManager commentManager = new CommentManager();
    private ThreadManager threadManager = new ThreadManager();
    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();

    /**
     * all visits to thread page
     * @param threadKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/{threadKey}", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey){
        ArrayList<Comment> comments = commentManager.loadComments(threadKey);
        Thread thread = threadManager.loadSingleThread(threadKey);
        Course course = courseManager.loadSingleCourse(courseKey);
        ModelAndView model = new ModelAndView();
        model.setViewName("instructorThread");
        model.addObject("currUserKey", WelcomeController.currUser.getUserKey());
        model.addObject("thread", thread);
        model.addObject("comments", comments);
        model.addObject("comment", new Comment());
        model.addObject("course", course);
        model.addObject("courseKey", courseKey);
        model.addObject("currUser", WelcomeController.currUser);

        return model;
    }

    /**
     * edit thread on forum page
     * @param threadKey thread key in datastore
     * @param thread edited thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/{threadKey}/editThread", method = RequestMethod.POST)
    public String editThread(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                             @ModelAttribute Thread thread){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        thread.setUserKey(WelcomeController.currUser.getUserKey());
        thread.setDate(df.format(today).toString());
        thread.setThreadKey(threadKey);
        thread.setInThreadName("Instructor");
        threadManager.editThread(thread);
        return "redirect:/instructor/"+courseKey+"/forum/"+threadKey;
    }

    /**
     * delete thread on forum page
     * @param threadKey thread key in datastore
     * @param thread deleted thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/{threadKey}/deleteThread", method = RequestMethod.POST)
    public String deleteThread(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                               @ModelAttribute Thread thread){
        threadManager.deleteThread(threadKey);
        return "redirect:/instructor/"+courseKey+"/forum";
    }

    /**
     * add/edit/delete comment on a thread
     * @param threadKey thread Key in datastore
     * @param comment add/edit/delete comment
     * @return course forum page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/{threadKey}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                             @ModelAttribute Comment comment){
        if (comment.getState().equals("add")){
            commentManager.addComment(threadKey, "Instructor", WelcomeController.currUser.getUserKey(),comment.getContent());
        } else if(comment.getState().equals("edit")){
            commentManager.editComment(comment.getCommentKey(), "Instructor", WelcomeController.currUser.getUserKey(), comment.getContent());
        } else if(comment.getState().equals("delete")){
            commentManager.deleteComment(comment.getCommentKey());
        }
        return "redirect:/instructor/"+courseKey+"/forum/"+threadKey;
    }

    /**
     * logout on learn theard page page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/instructor/{courseKey}/forum/{threadKey}/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                         @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
