package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetListTest {
    Tweet tweet1;
    Tweet tweet2;
    Tweet tweet3;
    Tweet tweet4;
    Tweet tweet5;
    TweetList tl;
    List<Tweet> tweetList = new ArrayList<>();
    List<Tweet> tweetList2 = new ArrayList<>();


    @BeforeEach
    void setUp() {
        tweet1 = new Tweet(1, "Poster1", "tweet1", new Date(915235200000L)); //1999. 01. 2. 0:00:00 GMT
        tweet2 = new Tweet(2, "Poster2", "tweet2", new Date(946771200000L)); //	2000.01.02.
        tweet3 = new Tweet(3, "Poster3", "tweet3", new Date(1236556800000L));  //209.03.09
        tweet4 = new Tweet(4, "Poster4", "tweet4", new Date(1331251200000L)); //2012.03.09.
        tweet5 = new Tweet(5, "Poster2", "tweet5", new Date(1546387200000L)); // 2019.01.02.
        TweetList.getInstance().addToTweets(tweet1);
        TweetList.getInstance().addToTweets(tweet2);
        TweetList.getInstance().addToTweets(tweet3);
        TweetList.getInstance().addToTweets(tweet4);
        TweetList.getInstance().addToTweets(tweet5);
        tweetList.add(tweet1);
        tweetList.add(tweet2);
        tweetList.add(tweet3);
        tweetList.add(tweet4);
        tweetList.add(tweet5);
    }

    @AfterEach
    void tearDown() {
        tweet1 = null;
        tweet2 = null;
        tweetList = null;
        TweetList.getInstance().nullList();
    }

    @Test
    void getInstance() {
        assertEquals(tweetList, TweetList.getInstance().getTweets() );
    }

    @Test
    void addToTweets() {
        assertEquals(5, TweetList.getInstance().getTweets().size());
        assertFalse(TweetList.getInstance().getTweets().isEmpty());
    }

    @Test
    void getTweets() {
        assertEquals(tweetList.get(0).getId(), TweetList.getInstance().getTweets().get(0).getId());
        assertEquals(tweetList.get(0), TweetList.getInstance().getTweets().get(0));
    }

    @Test
    void createFilteredListByPoster() {
        List<Tweet> filtered1 = TweetList.getInstance().createFilteredList(10,0, "Poster2", null);
        assertEquals(2, filtered1.size());
        assertEquals(tweet2, filtered1.get(0));
    }

    @Test
    void createFilteredListByDate() {
        List<Tweet> filtered1 = TweetList.getInstance().createFilteredList(10,0, "", new Date(1236556800000L));
        assertEquals(3, filtered1.size());
    }

    @Test
    void createFilteredListByDateAndPoster() {
        List<Tweet> filtered1 = TweetList.getInstance().createFilteredList(10,0, "Poster2", new Date(1236556800000L));
        assertEquals(1, filtered1.size());
    }
}
