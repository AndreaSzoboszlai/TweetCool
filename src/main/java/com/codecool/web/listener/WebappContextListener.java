package com.codecool.web.listener;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;
import com.codecool.web.service.XMLReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public final class WebappContextListener implements ServletContextListener {
    TweetList tweetList = new TweetList();
    XMLReader reader;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (tweetList.getFiles("./webapps/TweetCool") == true) {
            reader = new XMLReader("/home/andrea/Dokumentumok/apache-tomcat-9.0.16/webapps/TweetCool/Tweets.xml");
            for (Tweet element : reader.getTweets()) {
                tweetList.addTweet(element);
            }
        }
        System.out.println("This method is invoked once when the webapp gets deployed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
