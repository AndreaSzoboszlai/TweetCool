package com.codecool.web.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {
    private Tweet tweet;
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        tweet = new Tweet(20, "Poster", "Test Post", date);
    }

    @AfterEach
    void tearDown() {
        tweet = null;
    }

    @Test
    void getId() {
        assertEquals(20, tweet.getId());
    }

    @Test
    void getPosterName() {
        assertEquals("Poster", tweet.getPosterName());
    }

    @Test
    void getContent() {
        assertEquals("Test Post", tweet.getContent());
    }

    @Test
    void getTimestamp() {
        assertEquals(date, tweet.getTimestamp());
    }
}
