package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.*;
import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Controller for all visits to student/user progress pages.
 * use cases: load user progress for selected course
 * Created by aliao on 4/28/2017.
 */
@Controller
public class ProgressController {

    private CourseManager courseManager = new CourseManager();
    private EvaluationManager evaluationManager = new EvaluationManager();
    private HabitManager habitManager = new HabitManager();
    private GoalManager goalManager = new GoalManager();
    private SurveyManager surveyManager = new SurveyManager();
    private UserManager userManager = new UserManager();
    private HelperManager helper = new HelperManager();

    /**
     * all visit to course learn page
     * @return learn page
     */
    @RequestMapping(value = "/learn/{courseKey}", method = RequestMethod.GET)
    public ModelAndView learnCourse(@PathVariable("courseKey") String courseKey) {
        String hasHabit = "false";
        String hasGoal = "false";
        String hasEvaluation = "false";
        String hasSurvey = "false";
        String hasStarted = "false";
        ModelAndView model = new ModelAndView();
        model.setViewName("learnProgress");
        Course course = courseManager.loadSingleCourse(courseKey);
        model.addObject("courseTitle", course.getTitle());
        model.addObject("courseStartDate", course.getStartDate());
        model.addObject("courseKey", courseKey);
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
        if(courseManager.hasStarted(course.getStartDate())){
            hasStarted = "true";
        }
        model.addObject("hasSurvey", hasSurvey);
        model.addObject("hasEvaluation", hasEvaluation);
        model.addObject("hasHabit", hasHabit);
        model.addObject("hasGoal", hasGoal);
        model.addObject("hasStarted", hasStarted);
        model.addObject("currUser", WelcomeController.currUser);
        Date rawDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        String today = month + "/" + day + "/" + year;
        model.addObject("todayDate", today);
        model.addObject("courseProgress", getCourseProgress(course));

        return model;
    }

    /**
     * logout on learn single course page
     * @param user user logged out
     * @return welcome page
     */
    @RequestMapping(value = "/learn/{courseKey}/logout", method = RequestMethod.POST)
    public String logout(@PathVariable("courseKey") String courseKey, @RequestBody String user) {
        userManager.resetCurrentUser(new User());
        return "redirect:/";
    }

    /**
     * helper method to calculate the progress of course
     * course total days / passed days
     * @param course course utility object
     * @return
     */
    private int getCourseProgress(Course course){
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        Date today = new Date();
        Date startDate = helper.stringToDate(course.getStartDate());
        Date endDate = helper.stringToDate(course.getEndDate());
        cal1.setTime(startDate);
        cal2.setTime(endDate);
        int totalDays = daysBetween(cal1.getTime(), cal2.getTime());
        cal2.setTime(today);
        int dayspassed = daysBetween(cal1.getTime(), cal2.getTime());
        return (dayspassed/totalDays)*100;
    }

    /**
     * helper method for calculating days in between
     * @param d1 first date
     * @param d2 last date
     * @return amount of days in between the to dates
     */
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
