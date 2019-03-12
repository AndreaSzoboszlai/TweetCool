package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public final class TweetList {
    TweetListSingleton tweets = TweetListSingleton.getInstance();
    WriteToXml writer = new WriteToXml();


    public TweetList() {
    }

    public void createList(HttpServletRequest req) {
       writer.writeTweets(req, tweets.getTweets());
    }

    public void addTweet(HttpServletRequest req, Tweet tweet) {
        tweets.addToTweets(tweet);
        createList(req);
    }

    public List<Tweet> getTweets() {
        return tweets.getTweets();
    }

    public boolean getFiles(String filePath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

        for (File file : listOfFiles) {
            if (file.getName().equals("Tweets.xml")) {
                return true;
            }
        }
        return false;
    }
}
