package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TweetListSingleton {
    private static TweetListSingleton ourInstance = new TweetListSingleton();

    public static TweetListSingleton getInstance() {
        return ourInstance;
    }

    private TweetListSingleton() {
        WriteToXml writer = new WriteToXml();
        List<Tweet> tweets = new ArrayList<>();
    }
}
