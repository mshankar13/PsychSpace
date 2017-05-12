package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Comment;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Thread;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Path;
import java.util.ArrayList;

/**
 * Created by aliao on 5/10/2017.
 */
@Controller
public class ThreadController {

    private CommentManager commentManager = new CommentManager();
    private ThreadManager threadManager = new ThreadManager();
    private CourseManager courseManager = new CourseManager();
    private UserManager userManager = new UserManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private HabitManager habitManager = new HabitManager();
    private GoalManager goalManager = new GoalManager();
    private SurveyManager surveyManager = new SurveyManager();

    /**
     * all visits to thread page
     * @param courseKey course key in datastore
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/{threadKey}", method = RequestMethod.GET)
    public ModelAndView loadForum(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey){
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        String hasStarted = "false";
        ArrayList<Comment> comments = commentManager.loadComments(threadKey);
        Thread thread = threadManager.loadSingleThread(threadKey);
        Course course = courseManager.loadSingleCourse(courseKey);
        ModelAndView model = new ModelAndView();
        model.addObject("currUserKey", WelcomeController.currUser.getUserKey());
        model.addObject("thread", thread);
        model.setViewName("learnThread");
        model.addObject("comments", comments);
        model.addObject("comment", new Comment());
        model.addObject("course", course);
        if(evaluationManager.hasTodaysEvaluation(WelcomeController.currUser.getUserKey(), courseKey)){
            hasEvaluation = "true";
        }
        if(habitManager.loadUserHabit(WelcomeController.currUser.getUserKey(), courseKey) != null){
            hasHabit = "true";
        }
        if(goalManager.loadUserGoal(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasGoal = "true";
        }
        if(surveyManager.loadUserSurvey(courseKey, WelcomeController.currUser.getUserKey()) != null){
            hasSurvey = "true";
        }
        if(courseManager.hasStarted(courseManager.loadSingleCourse(courseKey).getStartDate())){
            hasStarted = "true";
        }
        model.addObject("hasSurvey", hasSurvey);
        model.addObject("hasEvaluation", hasEvaluation);
        model.addObject("hasHabit", hasHabit);
        model.addObject("hasGoal", hasGoal);
        model.addObject("hasStarted", hasStarted);


        return model;
    }

    /**
     * edit thread on forum page
     * @param courseKey course key in datastore
     * @param thread edited thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/{threadKey}/editThread", method = RequestMethod.POST)
    public String editThread(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                             @ModelAttribute Thread thread){
        threadManager.editThread(thread);
        return "redirect:/learn/"+courseKey+"forum"+threadKey;
    }

    /**
     * delete thread on forum page
     * @param courseKey course key in datastore
     * @param thread deleted thread
     * @return redirect to course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/{threadKey}/deleteThread", method = RequestMethod.POST)
    public String deleteThread(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                               @ModelAttribute Thread thread){
        threadManager.deleteThread(thread.getThreadKey());
        return "redirect:/learn/"+courseKey+"forum"+threadKey;
    }

    /**
     * add/edit/delete comment on a thread
     * @param courseKey courseKey
     * @param comment add/edit/delete comment
     * @return course forum page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/{threadKey}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable("courseKey") String courseKey, @PathVariable("threadKey") String threadKey,
                             @ModelAttribute Comment comment){
        String fullname = WelcomeController.currUser.getFirstName().concat(" ").concat(WelcomeController.currUser.getLastName());
        if (comment.getState().equals("add")){
            commentManager.addComment(threadKey, fullname, WelcomeController.currUser.getUserKey(),comment.getContent());
        } else if(comment.getState().equals("edit")){
            commentManager.editComment(comment.getCommentKey(), fullname, WelcomeController.currUser.getUserKey(), comment.getContent());
        } else if(comment.getState().equals("delete")){
            commentManager.deleteComment(comment.getCommentKey());
        }
        return "redirect:/learn/"+courseKey+"forum"+threadKey;
    }

    /**
     * logout on learn theard page page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/forum/{threadKey}/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }
}
