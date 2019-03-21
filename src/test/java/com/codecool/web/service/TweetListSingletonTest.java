package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetListSingletonTest {
    Tweet tweet1;
    Tweet tweet2;
    List<Tweet> tweetList = new ArrayList<>();
    List<Tweet> tweetList2 = new ArrayList<>();


    @BeforeEach
    void setUp() {
        tweet1 = new Tweet(1, "Poster1", "tweet1", new Date());
        tweet2 = new Tweet(2, "Poster2", "tweet2", new Date());
        TweetListSingleton.getInstance().addToTweets(tweet1);
        TweetListSingleton.getInstance().addToTweets(tweet2);
        tweetList.add(tweet1);
        tweetList.add(tweet2);
    }

    @AfterEach
    void tearDown() {
        tweet1 = null;
        tweet2 = null;
        tweetList = null;
    }

    @Test
    void getInstance() {
        assertEquals(tweetList, TweetListSingleton.getInstance().getTweets() );
    }

    @Test
    void addToTweets() {
        //assertEquals(2, TweetListSingleton.getInstance().getTweets().size());
        assertFalse(TweetListSingleton.getInstance().getTweets().isEmpty());
    }

    @Test
    void getTweets() {
        assertEquals(tweetList.get(0).getId(), TweetListSingleton.getInstance().getTweets().get(0).getId());
        assertEquals(tweetList.get(0), TweetListSingleton.getInstance().getTweets().get(0));
    }

    @Test
    void createFilteredList() {
    }
}
