package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TweetList {
    TweetListSingleton tweets = TweetListSingleton.getInstance();
    WriteToXml writer = new WriteToXml();


    public TweetList() {
    }

    public void createList() {
        writer.writeTweets(tweets.getTweets());
    }

    public void addTweet(Tweet tweet) {
        tweets.addToTweets(tweet);
        //createList();
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
