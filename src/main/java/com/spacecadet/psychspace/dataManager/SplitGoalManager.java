package com.spacecadet.psychspace.dataManager;

import com.spacecadet.psychspace.utilities.Course;
import com.spacecadet.psychspace.utilities.Goal;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.util.Date;

/**
 * Created by aliao on 5/6/2017.
 */
public class SplitGoalManager {

    private CourseManager courseManager = new CourseManager();
    private HelperManager helper = new HelperManager();

//    public Goal getSplitGoal(String courseKey, Goal goal){
//        Course course = courseManager.loadSingleCourse(courseKey);
//        if(goal != null){
//            Date startDate = helper.stringToDate(course.getStartDate());
//            Date endDate = helper.stringToDate(course.getEndDate());
//            int value = Integer.parseInt(goal.getValue());
//            int weeks = getWeeks(startDate, endDate);
//            goal.setValue()
//        }
//        return null;
//    }

    private int getWeeks(Date start, Date end){
        int weeks = 0;
        if(start != null && end != null){
            DateTime startTime = new DateTime(start);
            DateTime endTime = new DateTime(end);
            weeks = Weeks.weeksBetween(startTime, endTime).getWeeks();
        }
        return weeks;
    }
}
