package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TweetList {
    private final List<Tweet> tweets;
    WriteToXml writer = new WriteToXml();


    public TweetList() {
        tweets = new ArrayList<>();
    }

    public void createList() {
        writer.writeTweets(tweets);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        createList();
    }

    public List<Tweet> getTweets() {
        return tweets;
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
