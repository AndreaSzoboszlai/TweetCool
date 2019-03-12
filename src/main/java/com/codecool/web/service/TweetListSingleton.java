package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TweetListSingleton {
    private static TweetListSingleton ourInstance = new TweetListSingleton();
    private List<Tweet> tweets = null;

    public static TweetListSingleton getInstance() {
        return ourInstance;
    }

    private TweetListSingleton() {
        tweets = new ArrayList<>();
    }

    public void addToTweets(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
