package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetListSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/tweets")
public class TweetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));
        String name = req.getParameter("poster");
        String startDateString = req.getParameter("date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

        List<Tweet> tweets = TweetListSingleton.getInstance().getTweets();
        Date startDate = null;
        try {
            startDate = (Date) df.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Tweet> filtered = null;
        if (tweets != null && tweets.size() < limit) {
            limit = tweets.size();
        }

        if (tweets != null && tweets.size() < offset) {
            offset = 0;
        }

        filtered = createFilteredList(limit, offset, name, tweets, startDate);

        if (tweets != null) {
            req.setAttribute("filtered", filtered);
            req.getRequestDispatcher("tweetsjstl.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("noTweetYet.jsp").forward(req, resp);
        }


    }

    private List<Tweet> createFilteredList(int limit, int offset, String name, List<Tweet> tweets, Date startDate) {
        List<Tweet> filtered = new ArrayList<>();
        int count = 0;
        for (int i = offset; i < tweets.size(); i++) {
            if (count < limit) {
                if (name.equals("") && (startDate == null)) {
                    filtered.add(tweets.get(i));
                    count++;
                } else if (name.equals(tweets.get(i).getPosterName()) && (startDate == null)) {
                    filtered.add(tweets.get(i));
                    count++;
                } else if (name.equals(tweets.get(i).getPosterName())) {
                    long tweetTime = tweets.get(i).getTimestamp().getTime();
                    long givenTime = startDate.getTime();
                    if ((givenTime < tweetTime)) {
                        filtered.add(tweets.get(i));
                        count++;
                    }
                } else if (name.equals("") && (startDate != null)) {
                    long tweetTime = tweets.get(i).getTimestamp().getTime();
                    long givenTime = startDate.getTime();
                    if ((givenTime < tweetTime)) {
                        filtered.add(tweets.get(i));
                        count++;
                    }
                }
            }
        }
        return filtered;
    }
}
