package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;

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
        List<Tweet> tweets = (List<Tweet>) req.getSession().getAttribute("tweets");
        Date startDate = null;
        try {
            startDate = (Date)df.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //req.setAttribute("limit", limit);
        //req.setAttribute("offset", offset);
        //req.setAttribute("date", date);
        List<Tweet> filtered = null;
        if (tweets != null && tweets.size() < limit) {
            limit = tweets.size();
            filtered = createFilteredList(limit, offset, name, tweets, startDate);
        }


        if (tweets != null) {
            req.setAttribute("filtered", filtered);
            req.getRequestDispatcher("tweetsjstl.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("noTweetYet.jsp").forward(req, resp);
        }


    }

    private List<Tweet> createFilteredList(int limit, int offset, String name, List<Tweet> tweets, Date startDate) {
        List<Tweet> filtered = new ArrayList<>();
        for (int i = offset; i < limit; i++) {
            if (name.equals("") && (startDate == null)) {
                filtered.add(tweets.get(i));
            } else if (name.equals(tweets.get(i).getPosterName()) && (startDate == null)) {
                filtered.add(tweets.get(i));
            } else if (name.equals(tweets.get(i).getPosterName())) {
                compareDates(tweets, startDate, filtered, i);
            } else if (name.equals("") && (startDate != null)) {
                compareDates(tweets, startDate, filtered, i);
            }
        }
        return filtered;
    }

    private void compareDates(List<Tweet> tweets, Date startDate, List<Tweet> filtered, int i) {
        long tweetTime = tweets.get(i).getTimestamp().getTime();
        long givenTime = startDate.getTime();
        if ((givenTime < tweetTime)) {
            filtered.add(tweets.get(i));
        }
    }
}
