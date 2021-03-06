package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetList {
    private static TweetList ourInstance = new TweetList();
    private List<Tweet> tweets = null;

    public static TweetList getInstance() {
        return ourInstance;
    }

    private TweetList() {
        tweets = new ArrayList<>();
    }

    public void addToTweets(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public List<Tweet> createFilteredList(int limit, int offset, String name, Date startDate) {
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
                    if ((givenTime <= tweetTime)) {
                        filtered.add(tweets.get(i));
                        count++;
                    }
                } else if (name.equals("") && (startDate != null)) {
                    long tweetTime = tweets.get(i).getTimestamp().getTime();
                    long givenTime = startDate.getTime();
                    if ((givenTime <= tweetTime)) {
                        filtered.add(tweets.get(i));
                        count++;
                    }
                }
            }
        }
        return filtered;
    }

    public void nullList() {
        tweets = new ArrayList<>();
    }
}
