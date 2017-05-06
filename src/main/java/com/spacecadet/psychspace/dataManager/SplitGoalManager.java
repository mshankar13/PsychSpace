package com.spacecadet.psychspace.dataManager;

import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Goal;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Weeks;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by aliao on 5/6/2017.
 */
public class SplitGoalManager {

    private CourseManager courseManager = new CourseManager();
    private HelperManager helper = new HelperManager();

    /**
     * get the goal for each week with the weekly goal value
     * @param courseKey course key
     * @param goal goal
     * @return new goal with weekly value
     */
    public double getSplitGoalValue(String courseKey, Goal goal){
        Course course = courseManager.loadSingleCourse(courseKey);
        Date startDate = helper.stringToDate(course.getStartDate());
        Date endDate = helper.stringToDate(course.getEndDate());
        Date today = new Date();
        double value = Integer.parseInt(goal.getValue());
        double totalWeeks = getTotalWeeks(startDate, endDate);
        int currWeek = 0;
        while(today.before(endDate)){
            currWeek++;
            Date nextWeek= new Date(startDate.getTime()+7*24*60*60*1000);
            if(today.before(nextWeek)){
                DecimalFormat df = new DecimalFormat("#.00");
                double time = value/(currWeek/totalWeeks);
                return Double.valueOf(df.format(time));
            }
        }
        return 0;
    }

    /**
     * get the total amount of weeks between the two dates
     * @param start start date
     * @param end end date
     * @return int number of weeks
     */
    private int getTotalWeeks(Date start, Date end){
        int weeks = 0;
        if(start != null && end != null){
            DateTime startTime = new DateTime(start);
            DateTime endTime = new DateTime(end);
            weeks = Weeks.weeksBetween(startTime, endTime).getWeeks();
        }
        return weeks;
    }
}
