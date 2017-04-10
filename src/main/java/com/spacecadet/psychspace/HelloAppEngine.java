package com.spacecadet.psychspace;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.spacecadet.psychspace.dataManager.NewsManager;


public class HelloAppEngine extends HttpServlet {

  NewsManager entryManager = new NewsManager();
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
  Date date = new Date();
  entryManager.addNews("What Makes Self-Directed Learning Effective?", "Association for Psychological Science"
    , "While the benefits of self-directed learning are widely acknowledged, the reasons why a sense of control leads to better acquisition of material are poorly understood.",
            "5", "06/04/1995");

    response.setContentType("text/plain");
    response.getWriter().print(entryManager.loadNews());

  }
}
