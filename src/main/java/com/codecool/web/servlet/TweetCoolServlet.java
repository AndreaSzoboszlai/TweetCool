package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;
import com.codecool.web.service.XMLReader;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/new-tweet")
public class TweetCoolServlet extends HttpServlet {
    XMLReader reader;
    TweetList tweetList = new TweetList();
    private int id = 0;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Greeting> greetings = service.getGreetings();
        //req.setAttribute("greetings", greetings);

        String name = req.getParameter("name");
        String content = req.getParameter("content");
        Date date = new Date();
        id++;

        tweetList.addTweet(req, new Tweet(id, name, content, date));
        List<Tweet> tweets = tweetList.getTweets();
        req.getSession().setAttribute("tweets", tweets);
        req.setAttribute("name", name);
        req.setAttribute("content", content);
        req.setAttribute("date", date);
        req.getRequestDispatcher("tweets.jsp").forward(req, resp);
    }
}
