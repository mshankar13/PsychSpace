package com.spacecadet.psychspace.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marleneshankar on 3/27/17.
 */
public class Helpers {
    public static Date convertDate(String dateString) {
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        df.format(date);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
