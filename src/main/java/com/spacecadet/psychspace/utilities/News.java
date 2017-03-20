package com.spacecadet.psychspace.utilities;

import com.google.appengine.api.datastore.Key;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marleneshankar on 3/18/17.
 */
public class News {
    public String title;
    public String author;
    public String content;
    public int likesCount;
    public Date date;
    public String newsKey;

    public Date convertDate(String dateString) {
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
