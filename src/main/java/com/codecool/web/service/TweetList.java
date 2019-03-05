package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetList {
    private List<Tweet> tweets;

    public TweetList() {
        this.tweets = new ArrayList<>();
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
