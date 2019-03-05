package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/tweetcool")
public class TweetCoolServlet extends HttpServlet {
    private TweetList tweetList = new TweetList();
    private int id = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Greeting> greetings = service.getGreetings();
        //req.setAttribute("greetings", greetings);

        String name = req.getParameter("name");
        String content = req.getParameter("content");
        Date date = new Date();
        id++;
        tweetList.addTweet(new Tweet(id, name, content, date));
        List<Tweet> tweets = tweetList.getTweets();

        boolean jstl = Boolean.valueOf(req.getParameter("jstl"));
        if (jstl) {
            req.getRequestDispatcher("greeting-jstl.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("greeting.jsp").forward(req, resp);
        }
    }
}
