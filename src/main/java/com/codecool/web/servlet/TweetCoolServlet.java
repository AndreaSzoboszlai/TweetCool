package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetListSingleton;


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
    private int id = 0;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String content = req.getParameter("content");
        Date date = new Date();
        id++;

        TweetListSingleton.getInstance().addToTweets(new Tweet(id, name, content, date));
        List<Tweet> tweets = TweetListSingleton.getInstance().getTweets();
        req.getSession().setAttribute("tweets", tweets);
        req.setAttribute("name", name);
        req.setAttribute("content", content);
        req.setAttribute("date", date);
        req.getRequestDispatcher("tweets.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tweet> tweets = TweetListSingleton.getInstance().getTweets();
        req.getSession().setAttribute("filtered", tweets);
        req.getRequestDispatcher("tweetsjstl.jsp").forward(req, resp);
    }
}
